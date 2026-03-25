package io.github.hyperliquid.sdk;

import io.github.hyperliquid.sdk.model.order.TwapCancelResult;
import io.github.hyperliquid.sdk.model.order.TwapOrderResult;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TwapOrderIntegrationTest extends IntegrationTestBase {

    @Test
    void placeThenCancelTwapOrder() {
        // Place a small TWAP buy of 0.05 ETH over 5 minutes on testnet (~$80, above $50 min)
        TwapOrderResult result = client.getExchange()
            .placeTwapOrder("ETH", true, "0.05", 5, false);

        assertNotNull(result, "placeTwapOrder should return a result");
        assertNotNull(result.getTwapId(), "twapId must be present");

        // Immediately cancel it
        TwapCancelResult cancel = client.getExchange()
            .cancelTwapOrder("ETH", result.getTwapId());

        assertNotNull(cancel, "cancelTwapOrder should return a result");
        assertNotNull(cancel.getTwapId(), "cancelled twapId must be present");
    }
}
