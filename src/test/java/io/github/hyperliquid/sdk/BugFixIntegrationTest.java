package io.github.hyperliquid.sdk;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.hyperliquid.sdk.model.info.ClearinghouseState;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class BugFixIntegrationTest extends IntegrationTestBase {

    @Test
    void allMidsReturnsNonEmptyMap() {
        Map<String, String> result = client.getInfo().allMids();
        assertNotNull(result, "allMids() should return non-null");
        assertFalse(result.isEmpty(), "allMids() should return non-empty map");
    }

    @Test
    void clearinghouseStateReturnsNonNull() {
        ClearinghouseState result = client.getInfo().clearinghouseState(walletAddress);
        assertNotNull(result, "clearinghouseState() should return non-null");
    }

    @Test
    void historicalOrdersReturnsNonNull() {
        JsonNode result = client.getInfo().historicalOrders(walletAddress);
        assertNotNull(result, "historicalOrders() should return non-null");
    }

    @Test
    void querySpotDeployAuctionStatusReturnsNonNull() {
        JsonNode result = client.getInfo().querySpotDeployAuctionStatus(walletAddress);
        assertNotNull(result, "querySpotDeployAuctionStatus() should return non-null");
    }

    @Test
    void queryUserDexAbstractionStateReturnsNonNull() {
        JsonNode result = client.getInfo().queryUserDexAbstractionState(walletAddress);
        assertNotNull(result, "queryUserDexAbstractionState() should return non-null");
    }

    @Test
    void userFundingHistoryReturnsNonNull() {
        long endMs = System.currentTimeMillis();
        long startMs = endMs - 30L * 24 * 60 * 60 * 1000; // 30 days ago
        JsonNode result = client.getInfo().userFundingHistory(walletAddress, "ETH", startMs, endMs);
        assertNotNull(result, "userFundingHistory() should return non-null");
    }
}
