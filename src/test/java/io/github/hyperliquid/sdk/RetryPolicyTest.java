package io.github.hyperliquid.sdk;

import io.github.hyperliquid.sdk.utils.HypeError;
import io.github.hyperliquid.sdk.utils.RetryPolicy;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

public class RetryPolicyTest {

    private final RetryPolicy policy = new RetryPolicy(2, 10, 1000, 2.0);

    @Test
    void successOnFirstAttemptNoRetry() throws Exception {
        AtomicInteger calls = new AtomicInteger();
        String result = policy.execute(() -> {
            calls.incrementAndGet();
            return "ok";
        });
        assertEquals("ok", result);
        assertEquals(1, calls.get());
    }

    @Test
    void retriesOnServerErrorAndSucceeds() throws Exception {
        AtomicInteger calls = new AtomicInteger();
        String result = policy.execute(() -> {
            int n = calls.incrementAndGet();
            if (n < 3) throw new HypeError.ServerHypeError(500, "server error");
            return "ok";
        });
        assertEquals("ok", result);
        assertEquals(3, calls.get());
    }

    @Test
    void clientErrorRethrowsImmediately() {
        AtomicInteger calls = new AtomicInteger();
        assertThrows(HypeError.ClientHypeError.class, () -> policy.execute(() -> {
            calls.incrementAndGet();
            throw new HypeError.ClientHypeError(400, "bad request");
        }));
        assertEquals(1, calls.get());
    }

    @Test
    void exhaustsRetriesAndRethrowsLastException() {
        AtomicInteger calls = new AtomicInteger();
        HypeError.ServerHypeError ex = assertThrows(HypeError.ServerHypeError.class, () ->
            policy.execute(() -> {
                calls.incrementAndGet();
                throw new HypeError.ServerHypeError(503, "unavailable");
            })
        );
        assertEquals(503, ex.getStatusCode());
        assertEquals(3, calls.get());
    }

    @Test
    void backoffIsExponential() throws Exception {
        RetryPolicy fastP = new RetryPolicy(2, 10, 1000, 2.0);
        AtomicInteger calls = new AtomicInteger();
        long start = System.currentTimeMillis();
        assertThrows(Exception.class, () -> fastP.execute(() -> {
            calls.incrementAndGet();
            throw new HypeError.ServerHypeError(500, "err");
        }));
        long elapsed = System.currentTimeMillis() - start;
        assertTrue(elapsed >= 25, "Expected at least 25ms elapsed for exponential backoff, got " + elapsed);
        assertEquals(3, calls.get());
    }
}
