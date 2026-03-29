package io.github.hyperliquid.sdk;

import io.github.hyperliquid.sdk.model.info.*;
import org.junit.jupiter.api.Test;

import java.util.List;
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
        List<HistoricalOrder> result = client.getInfo().historicalOrders(walletAddress);
        assertNotNull(result, "historicalOrders() should return non-null");
    }

    @Test
    void querySpotDeployAuctionStatusReturnsNonNull() {
        SpotDeployState result = client.getInfo().querySpotDeployAuctionStatus(walletAddress);
        assertNotNull(result, "querySpotDeployAuctionStatus() should return non-null");
    }

    @Test
    void queryUserDexAbstractionStateReturnsNonNull() {
        boolean result = client.getInfo().queryUserDexAbstractionState(walletAddress);
        // boolean is always non-null, just verify call succeeds
    }

    @Test
    void userFundingHistoryReturnsNonNull() {
        long endMs = System.currentTimeMillis();
        long startMs = endMs - 30L * 24 * 60 * 60 * 1000; // 30 days ago
        List<UserFundingEntry> result = client.getInfo().userFundingHistory(walletAddress, "ETH", startMs, endMs);
        assertNotNull(result, "userFundingHistory() should return non-null");
    }
}
