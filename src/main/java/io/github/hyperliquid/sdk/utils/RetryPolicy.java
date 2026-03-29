package io.github.hyperliquid.sdk.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.Callable;

/**
 * Retry policy configuration, supports exponential backoff.
 */
public final class RetryPolicy {

    private static final Logger log = LoggerFactory.getLogger(RetryPolicy.class);

    private final int maxRetries;
    private final long initialBackoffMillis;
    private final long maxBackoffMillis;
    private final double backoffMultiplier;

    public RetryPolicy(int maxRetries, long initialBackoffMillis, long maxBackoffMillis, double backoffMultiplier) {
        this.maxRetries = Math.max(0, maxRetries);
        this.initialBackoffMillis = Math.max(0, initialBackoffMillis);
        this.maxBackoffMillis = Math.max(initialBackoffMillis, maxBackoffMillis);
        this.backoffMultiplier = backoffMultiplier <= 1.0 ? 2.0 : backoffMultiplier;
    }

    public static RetryPolicy defaultPolicy() {
        return new RetryPolicy(3, 500, 5000, 2.0);
    }

    public int getMaxRetries() { return maxRetries; }
    public long getInitialBackoffMillis() { return initialBackoffMillis; }
    public long getMaxBackoffMillis() { return maxBackoffMillis; }
    public double getBackoffMultiplier() { return backoffMultiplier; }

    /**
     * Execute a callable with retry on transient failures.
     * Retries only on ServerHypeError (5xx) and HypeError caused by IOException.
     * Does NOT retry on ClientHypeError (4xx).
     */
    public <T> T execute(Callable<T> action) throws Exception {
        Exception lastException = null;
        for (int attempt = 0; attempt <= maxRetries; attempt++) {
            try {
                return action.call();
            } catch (HypeError.ClientHypeError e) {
                throw e;
            } catch (HypeError.ServerHypeError e) {
                lastException = e;
                if (attempt < maxRetries) {
                    long sleepMs = computeBackoff(attempt);
                    log.warn("Server error on attempt {}/{}, retrying in {}ms: {}", attempt + 1, maxRetries + 1, sleepMs, e.getMessage());
                    Thread.sleep(sleepMs);
                }
            } catch (HypeError e) {
                if (e.getCause() instanceof IOException) {
                    lastException = e;
                    if (attempt < maxRetries) {
                        long sleepMs = computeBackoff(attempt);
                        log.warn("IO error on attempt {}/{}, retrying in {}ms: {}", attempt + 1, maxRetries + 1, sleepMs, e.getMessage());
                        Thread.sleep(sleepMs);
                    }
                } else {
                    throw e;
                }
            }
        }
        throw lastException;
    }

    private long computeBackoff(int attempt) {
        double backoff = initialBackoffMillis * Math.pow(backoffMultiplier, attempt);
        return Math.min((long) backoff, maxBackoffMillis);
    }
}
