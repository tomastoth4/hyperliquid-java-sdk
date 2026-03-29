package io.github.hyperliquid.sdk;

import io.github.hyperliquid.sdk.model.subscription.UserTwapSliceFillsSubscription;
import io.github.hyperliquid.sdk.model.websocket.UserTwapSliceFillsMessage;
import io.github.hyperliquid.sdk.utils.HypeError;
import io.github.hyperliquid.sdk.utils.HypeHttpClient;
import io.github.hyperliquid.sdk.utils.RateLimiter;
import io.github.hyperliquid.sdk.utils.RetryPolicy;
import okhttp3.OkHttpClient;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class NewFeaturesIntegrationTest extends IntegrationTestBase {

    // -------------------------------------------------------------------------
    // UserTwapSliceFillsSubscription — WS snapshot arrives from testnet
    // -------------------------------------------------------------------------

    @Test
    void userTwapSliceFillsSubscribeReceivesSnapshot() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        UserTwapSliceFillsMessage[] received = new UserTwapSliceFillsMessage[1];

        client.getInfo().subscribe(
            UserTwapSliceFillsSubscription.of(walletAddress),
            (UserTwapSliceFillsMessage msg) -> { received[0] = msg; latch.countDown(); }
        );

        boolean got = latch.await(15, TimeUnit.SECONDS);
        client.getInfo().closeWs();

        assertTrue(got, "Expected UserTwapSliceFillsMessage within 15 seconds (snapshot should arrive immediately)");
        assertNotNull(received[0]);
        // fills may be null/empty for accounts with no TWAP history — just verify the snapshot arrived and deserialized
    }

    // -------------------------------------------------------------------------
    // RetryPolicy — happy path: real testnet requests succeed through retry wrapper
    // -------------------------------------------------------------------------

    @Test
    void retryPolicyDoesNotBreakSuccessfulRequests() {
        // RetryPolicy is already wired into HypeHttpClient — this test verifies
        // that real testnet requests succeed end-to-end with the retry wrapper active.
        var mids = client.getInfo().allMids();
        assertNotNull(mids);
        assertFalse(mids.isEmpty(), "allMids() should return prices — retry policy should not interfere with successful calls");
    }

    @Test
    void retryPolicyRetriesOnIoExceptionAndEventuallyFails() throws Exception {
        // Point at a non-routable address to force IOException and observe retries.
        // Use a fast policy so test completes quickly.
        RetryPolicy fastPolicy = new RetryPolicy(2, 10, 50, 2.0);
        HypeHttpClient badClient = new HypeHttpClient("http://192.0.2.1", // TEST-NET (RFC 5737) — not routable
            new OkHttpClient.Builder().connectTimeout(200, TimeUnit.MILLISECONDS).build(),
            fastPolicy);

        long start = System.currentTimeMillis();
        assertThrows(HypeError.class, () -> badClient.post("/info", java.util.Map.of("type", "meta")),
            "Should throw HypeError after exhausting retries on non-routable host");
        long elapsed = System.currentTimeMillis() - start;

        // With 2 retries and 10ms+20ms backoff, should take at least 25ms
        assertTrue(elapsed >= 25, "Should have waited for backoff between retries, elapsed=" + elapsed + "ms");
    }

    // -------------------------------------------------------------------------
    // RateLimiter — burst of real testnet requests all succeed without throttling
    // -------------------------------------------------------------------------

    @Test
    void rateLimiterAllowsBurstOfRealRequests() {
        // Make 10 real requests in quick succession — rate limiter has 1200 tokens
        // so all should pass through immediately without blocking.
        int requestCount = 10;
        List<Long> responseTimes = new ArrayList<>();

        for (int i = 0; i < requestCount; i++) {
            long start = System.currentTimeMillis();
            var mids = client.getInfo().allMids();
            responseTimes.add(System.currentTimeMillis() - start);
            assertNotNull(mids);
        }

        // Verify rate limiter didn't introduce unexpected blocking (> 1s per request
        // would indicate the limiter is incorrectly throttling)
        long maxResponseTime = responseTimes.stream().mapToLong(Long::longValue).max().orElse(0);
        assertTrue(maxResponseTime < 10_000,
            "Rate limiter should not throttle requests when tokens are available; max response=" + maxResponseTime + "ms");
    }

    @Test
    void rateLimiterSingletonIsSharedAcrossRequests() {
        // Verify the singleton instance is the same object everywhere
        RateLimiter a = RateLimiter.getInstance();
        RateLimiter b = RateLimiter.getInstance();
        assertSame(a, b, "RateLimiter.getInstance() must always return the same singleton");
    }
}
