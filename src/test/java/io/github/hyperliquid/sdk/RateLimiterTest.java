package io.github.hyperliquid.sdk;

import io.github.hyperliquid.sdk.utils.RateLimiter;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

public class RateLimiterTest {

    @Test
    void acquireSucceedsImmediatelyWhenTokensAvailable() {
        RateLimiter rl = new RateLimiter(10, 100);
        long start = System.currentTimeMillis();
        rl.acquire();
        long elapsed = System.currentTimeMillis() - start;
        assertTrue(elapsed < 50, "acquire() should return immediately, took " + elapsed + "ms");
    }

    @Test
    void tryAcquireReturnsTrueWhenTokensAvailable() {
        RateLimiter rl = new RateLimiter(10, 100);
        assertTrue(rl.tryAcquire());
    }

    @Test
    void tryAcquireReturnsFalseWhenExhausted() {
        RateLimiter rl = new RateLimiter(3, 0.01);
        assertTrue(rl.tryAcquire());
        assertTrue(rl.tryAcquire());
        assertTrue(rl.tryAcquire());
        assertFalse(rl.tryAcquire());
    }

    @Test
    void tokensRefillOverTime() throws InterruptedException {
        RateLimiter rl = new RateLimiter(2, 100);
        assertTrue(rl.tryAcquire());
        assertTrue(rl.tryAcquire());
        assertFalse(rl.tryAcquire());
        Thread.sleep(50);
        assertTrue(rl.tryAcquire());
    }

    @Test
    void concurrentAccessExactly100Acquisitions() throws InterruptedException {
        int maxTokens = 100;
        RateLimiter rl = new RateLimiter(maxTokens, 0.001);
        int threads = 10;
        int callsPerThread = 10;

        AtomicInteger successes = new AtomicInteger();
        CountDownLatch ready = new CountDownLatch(threads);
        CountDownLatch start = new CountDownLatch(1);
        CountDownLatch done = new CountDownLatch(threads);

        for (int i = 0; i < threads; i++) {
            new Thread(() -> {
                ready.countDown();
                try { start.await(); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
                for (int j = 0; j < callsPerThread; j++) {
                    if (rl.tryAcquire()) successes.incrementAndGet();
                }
                done.countDown();
            }).start();
        }

        ready.await();
        start.countDown();
        done.await();

        assertEquals(maxTokens, successes.get(),
            "Exactly 100 tryAcquire() calls should succeed (one per token)");
    }
}
