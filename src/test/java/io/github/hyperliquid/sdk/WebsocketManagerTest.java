package io.github.hyperliquid.sdk;

import io.github.hyperliquid.sdk.model.subscription.UserEventsSubscription;
import io.github.hyperliquid.sdk.websocket.WebsocketManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WebsocketManagerTest {

    private WebsocketManager wm;

    @AfterEach
    void tearDown() {
        if (wm != null) wm.stop();
    }

    @Test
    void defaultConfigMaxBackoffIsFiveSeconds() {
        wm = new WebsocketManager("https://test.example.com");
        assertEquals(5_000L, wm.getConfigMaxBackoffMs(),
            "Default configMaxBackoffMs should be 5000ms after the spec change");
    }

    @Test
    void userEventsSubscriptionToIdentifierReturnsType() {
        UserEventsSubscription sub = UserEventsSubscription.create();
        assertEquals("userEvents", sub.toIdentifier());
    }
}
