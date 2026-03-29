package io.github.hyperliquid.sdk.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Thread-safe token bucket rate limiter.
 * Singleton uses 1200 max tokens, 20 tokens/second refill rate.
 */
public class RateLimiter {

    private static final Logger log = LoggerFactory.getLogger(RateLimiter.class);

    private static final RateLimiter INSTANCE = new RateLimiter(1200, 20);

    private final int maxTokens;
    private final double refillRatePerSecond;

    private double tokens;
    private long lastRefillNanos;

    public RateLimiter(int maxTokens, double refillRatePerSecond) {
        this.maxTokens = maxTokens;
        this.refillRatePerSecond = refillRatePerSecond;
        this.tokens = maxTokens;
        this.lastRefillNanos = System.nanoTime();
    }

    public static RateLimiter getInstance() {
        return INSTANCE;
    }

    /**
     * Acquire a token, blocking until one is available.
     */
    public synchronized void acquire() {
        while (true) {
            refill();
            if (tokens >= 1.0) {
                tokens -= 1.0;
                return;
            }
            double tokensNeeded = 1.0 - tokens;
            long waitMs = (long) Math.ceil(tokensNeeded / refillRatePerSecond * 1000);
            waitMs = Math.max(1, waitMs);
            log.debug("Rate limit reached, waiting {}ms", waitMs);
            try {
                wait(waitMs);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

    /**
     * Try to acquire a token without blocking.
     * @return true if a token was acquired, false if none available
     */
    public synchronized boolean tryAcquire() {
        refill();
        if (tokens >= 1.0) {
            tokens -= 1.0;
            return true;
        }
        return false;
    }

    private void refill() {
        long now = System.nanoTime();
        double elapsedSeconds = (now - lastRefillNanos) / 1_000_000_000.0;
        double newTokens = elapsedSeconds * refillRatePerSecond;
        if (newTokens > 0) {
            tokens = Math.min(maxTokens, tokens + newTokens);
            lastRefillNanos = now;
        }
    }
}
