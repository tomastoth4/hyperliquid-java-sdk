package io.github.hyperliquid.sdk;

import io.github.hyperliquid.sdk.model.subscription.*;
import io.github.hyperliquid.sdk.model.websocket.*;
import org.junit.jupiter.api.Test;
import java.util.concurrent.*;
import static org.junit.jupiter.api.Assertions.*;

public class WsSubscriptionIntegrationTest extends IntegrationTestBase {

    @Test
    void userFillsTypedSubscribeReceivesSnapshot() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        UserFillsMessage[] received = new UserFillsMessage[1];

        client.getInfo().subscribe(
            UserFillsSubscription.of(walletAddress),
            (UserFillsMessage msg) -> { received[0] = msg; latch.countDown(); }
        );

        boolean got = latch.await(15, TimeUnit.SECONDS);
        client.getInfo().closeWs();

        assertTrue(got, "Expected UserFillsMessage within 15 seconds (snapshot should arrive immediately)");
        assertNotNull(received[0]);
        // Snapshot may have 0 fills if account is new — just check message arrived
    }

    @Test
    void orderUpdatesTypedSubscribeCompiles() throws Exception {
        // Verify typed overload for existing OrderUpdatesSubscription works
        CountDownLatch latch = new CountDownLatch(1);
        client.getInfo().subscribe(
            OrderUpdatesSubscription.of(walletAddress),
            (OrderUpdateMessage msg) -> latch.countDown()
        );
        // Don't wait for fill — just verify no compilation/runtime error on subscribe call
        client.getInfo().closeWs();
    }
}
