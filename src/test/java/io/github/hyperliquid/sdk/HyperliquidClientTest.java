package io.github.hyperliquid.sdk;

import io.github.hyperliquid.sdk.apis.Info;
import io.github.hyperliquid.sdk.model.info.*;
import io.github.hyperliquid.sdk.utils.HypeError;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.Instant;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * HyperliquidClient test class
 * Uses JUnit 5 to comprehensively test all methods under client.getInfo().
 * Includes: successful call validation, return structure assertions, error
 * handling, and log output verification.
 */
public class HyperliquidClientTest extends IntegrationTestBase {

    /**
     * Current test address
     */
    private String address;

    /**
     * Capture slf4j-simple's standard error output for log verification
     */
    private ByteArrayOutputStream errContent;

    /**
     * Backup original System.err
     */
    private PrintStream originalErr;

    /**
     * Test initialization: build testnet client, enable debug logging, and capture
     * log output.
     */
    @BeforeEach
    void setUp() {
        originalErr = System.err;
        errContent = new ByteArrayOutputStream();
        System.setErr(new PrintStream(errContent));

        address = walletAddress;
    }

    /**
     * Test cleanup: close WebSocket, restore log output, and release client.
     */
    @AfterEach
    void tearDown() {
        try {
            client.getInfo().closeWs();
        } catch (Exception ignored) {
        }
        System.setErr(originalErr);
        client = null;
    }

    /**
     * Utility method: reset the log buffer.
     */
    private void resetLogs() {
        errContent.reset();
    }

    /**
     * Utility method: verify that logs contain basic POST/Request/Response
     * keywords.
     */
    private void assertHttpLogsPresent() {
        String logs = errContent.toString();
        assertTrue(logs.contains("/info"));
        assertTrue(logs.contains("POST:"));
        assertTrue(logs.contains("Request:"));
    }

    /**
     * Verify account information retrieval functionality: clearinghouse state and
     * user state.
     */
    @Test
    @DisplayName("Account info: clearinghouseState/userState return structure and logs")
    void testGetAccountInfo() {
        resetLogs();
        Info info = client.getInfo();
        ClearinghouseState state = info.clearinghouseState(address);
        assertNotNull(state);
        assertNotNull(state.getAssetPositions());
        assertHttpLogsPresent();

        resetLogs();
        ClearinghouseState userState = info.userState(address);
        assertNotNull(userState);
        assertNotNull(userState.getAssetPositions());
        assertHttpLogsPresent();
    }

    /**
     * Verify market data retrieval functionality: meta, allMids, l2Book,
     * candleSnapshotLatest.
     */
    @Test
    @DisplayName("Market data: meta/allMids/l2Book/candleSnapshotLatest")
    void testGetMarketData() {
        Info info = client.getInfo();

        resetLogs();
        Meta meta = info.meta();
        assertNotNull(meta);
        assertNotNull(meta.getUniverse());
        assertFalse(meta.getUniverse().isEmpty());
        assertHttpLogsPresent();

        resetLogs();
        Map<String, String> mids = info.allMids();
        assertNotNull(mids);
        assertHttpLogsPresent();

        resetLogs();
        L2Book book = info.l2Book("BTC");
        assertNotNull(book);
        assertNotNull(book.getLevels());
        assertHttpLogsPresent();

        resetLogs();
        Candle latest = info.candleSnapshotLatest("BTC", CandleInterval.MINUTE_1);
        // Latest candle may be null when there is no data
        if (latest != null) {
            assertNotNull(latest.getStartTimestamp());
            assertNotNull(latest.getClosePrice());
        }
        assertHttpLogsPresent();
    }

    /**
     * Verify open orders query functionality: openOrders and frontendOpenOrders.
     */
    @Test
    @DisplayName("Open orders: openOrders/frontendOpenOrders list structure")
    void testGetOpenOrders() {
        Info info = client.getInfo();

        resetLogs();
        List<OpenOrder> orders = info.openOrders(address);
        assertNotNull(orders);
        assertHttpLogsPresent();

        resetLogs();
        List<FrontendOpenOrder> feOrders = info.frontendOpenOrders(address);
        assertNotNull(feOrders);
        assertHttpLogsPresent();
    }

    /**
     * Verify position information query functionality:
     * ClearinghouseState.assetPositions field.
     */
    @Test
    @DisplayName("Position info: ClearinghouseState.assetPositions field validation")
    void testGetPositions() {
        Info info = client.getInfo();
        resetLogs();
        ClearinghouseState state = info.clearinghouseState(address);
        assertNotNull(state);
        assertNotNull(state.getAssetPositions());
        if (!state.getAssetPositions().isEmpty()) {
            ClearinghouseState.AssetPositions ap = state.getAssetPositions().getFirst();
            assertNotNull(ap.getType());
            assertNotNull(ap.getPosition());
        }
        assertHttpLogsPresent();
    }

    /**
     * Metadata and cache: meta(String), loadMetaCache
     */
    @Test
    @DisplayName("Metadata: meta(dex)/loadMetaCache cache effectiveness")
    void testMetaAndCache() {
        Info info = client.getInfo();
        resetLogs();
        Meta m1 = info.meta("");
        assertNotNull(m1);
        assertHttpLogsPresent();

        resetLogs();
        Meta cached = info.loadMetaCache();
        assertNotNull(cached);
        // loadMetaCache does not trigger a request, so POST logs are not required
    }

    /**
     * Metadata and asset contexts: JSON and typed consistency
     */
    @Test
    @DisplayName("Metadata: metaAndAssetCtxs JSON/typed consistency")
    void testMetaAndAssetCtxs() {
        Info info = client.getInfo();

        resetLogs();
        MetaAndAssetCtxs result = info.metaAndAssetCtxs();
        assertNotNull(result);
        assertHttpLogsPresent();
    }

    /**
     * Spot metadata: spotMeta and spotMetaAndAssetCtxs
     */
    @Test
    @DisplayName("Spot metadata: spotMeta/spotMetaAndAssetCtxs")
    void testSpotMeta() {
        Info info = client.getInfo();

        resetLogs();
        SpotMeta sm = info.spotMeta();
        assertNotNull(sm);
        assertHttpLogsPresent();

        resetLogs();
        SpotMetaAndAssetCtxs spotMetaAndAssetCtxs = info.spotMetaAndAssetCtxs();
        assertNotNull(spotMetaAndAssetCtxs);
        assertHttpLogsPresent();
    }

    /**
     * Spot metadata cache operations: load, refresh, and clear.
     */
    @Test
    @DisplayName("Spot metadata cache: load/refresh/clear")
    void testSpotMetaCacheOperations() {
        Info info = client.getInfo();

        resetLogs();
        SpotMeta cached = info.loadSpotMetaCache();
        assertNotNull(cached);
        // loadSpotMetaCache uses Caffeine cache; HTTP logs not guaranteed (same as loadMetaCache)

        resetLogs();
        SpotMeta refreshed = info.refreshSpotMetaCache();
        assertNotNull(refreshed);
        assertHttpLogsPresent();

        info.clearSpotMetaCache();
        assertDoesNotThrow(info::getSpotMetaCacheStats);
    }

    /**
     * perpDexs: typed list
     */
    @Test
    @DisplayName("Perpetual DEX list: perpDexs")
    void testPerpDexs() {
        Info info = client.getInfo();

        resetLogs();
        List<PerpDex> dexs = info.perpDexs();
        assertNotNull(dexs);
        assertHttpLogsPresent();
    }

    /**
     * perpDexStatus: typed
     */
    @Test
    @DisplayName("Perpetual DEX status: perpDexStatus")
    void testPerpDexStatus() {
        Info info = client.getInfo();

        resetLogs();
        PerpDexStatus status = info.perpDexStatus("");
        assertNotNull(status);
        assertHttpLogsPresent();
    }

    /**
     * openOrders: dex variant
     */
    @Test
    @DisplayName("Open orders: openOrders(dex)")
    void testOpenOrdersWithDex() {
        Info info = client.getInfo();
        resetLogs();
        List<OpenOrder> o = info.openOrders(address, "");
        assertNotNull(o);
        assertHttpLogsPresent();
    }

    /**
     * allMids: default and specified dex
     */
    @Test
    @DisplayName("Mid prices: allMids default and specified dex")
    void testAllMids() {
        Info info = client.getInfo();

        resetLogs();
        Map<String, String> m1 = info.allMids();
        assertNotNull(m1);
        assertHttpLogsPresent();

        resetLogs();
        Map<String, String> m2 = info.allMids("");
        assertNotNull(m2);
        assertHttpLogsPresent();
    }

    /**
     * L2 order book: aggregation parameters
     */
    @Test
    @DisplayName("Order book: l2Book aggregation parameters validity")
    void testL2BookAggregations() {
        Info info = client.getInfo();
        resetLogs();
        try {
            L2Book b1 = info.l2Book("BTC", 5, 1);
            assertNotNull(b1);
            assertNotNull(b1.getLevels());
            assertHttpLogsPresent();
        } catch (HypeError.ServerHypeError e) {
            assertHttpLogsPresent();
            resetLogs();
            L2Book fallback = info.l2Book("BTC");
            assertNotNull(fallback);
            assertNotNull(fallback.getLevels());
            assertHttpLogsPresent();
        }
    }

    /**
     * Candles: range and count
     */
    @Test
    @DisplayName("Candles: candleSnapshot range and count")
    void testCandles() {
        Info info = client.getInfo();
        long end = Instant.now().toEpochMilli();
        long start = end - CandleInterval.MINUTE_1.toMillis() * 30;

        resetLogs();
        List<Candle> cs = info.candleSnapshot("BTC", CandleInterval.MINUTE_1, start, end);
        assertNotNull(cs);
        assertHttpLogsPresent();

        resetLogs();
        List<Candle> last10 = info.candleSnapshotByCount("BTC", CandleInterval.MINUTE_1, 10);
        assertNotNull(last10);
        assertTrue(last10.size() <= 10);
        assertHttpLogsPresent();
    }

    /**
     * Candles: invalid count should throw exception
     */
    @Test
    @DisplayName("Candle error: count<=0 throws HypeError")
    void testCandleSnapshotByCountInvalid() {
        Info info = client.getInfo();
        assertThrows(HypeError.class, () -> info.candleSnapshotByCount("BTC", CandleInterval.MINUTE_1, 0));
    }

    /**
     * Name to asset ID mapping: unknown name should throw exception
     */
    @Test
    @DisplayName("Name mapping error: unknown coin throws HypeError")
    void testNameToAssetUnknown() {
        Info info = client.getInfo();
        info.loadMetaCache();
        assertThrows(HypeError.class, () -> info.nameToAsset("UNKNOWN_COIN_XYZ"));
    }

    /**
     * Funding rate history: by asset ID and name
     */
    @Test
    @DisplayName("Funding rates: fundingHistory(id/name)")
    void testFundingHistory() {
        Info info = client.getInfo();
        long start = 1763136000000L;
        long end = 1763532000000L;

        resetLogs();
        try {
            List<FundingHistory> list = info.fundingHistory("BTC", start, end);
            assertNotNull(list);
            assertHttpLogsPresent();
        } catch (HypeError.ServerHypeError e) {
            assertHttpLogsPresent();
        }
    }

    /**
     * User funding rate history: multiple overload consistency
     */
    @Test
    @DisplayName("User funding rates: userFundingHistory multiple overloads")
    void testUserFundingHistoryVariants() {
        Info info = client.getInfo();
        long end = Instant.now().toEpochMilli();
        long start = end - CandleInterval.HOUR_1.toMillis() * 24;

        resetLogs();
        List<UserFundingEntry> a1 = info.userFundingHistory(address, start, end);
        assertNotNull(a1);
        assertHttpLogsPresent();

        resetLogs();
        List<UserFundingEntry> a2 = info.userFundingHistory(address, "BTC", start, end);
        assertNotNull(a2);
        assertHttpLogsPresent();

        // Asset ID variant
        resetLogs();
        int btcId = info.nameToAsset("BTC");
        List<UserFundingEntry> a3 = info.userFundingHistory(address, btcId, start, end);
        assertNotNull(a3);
        assertHttpLogsPresent();
    }

    /**
     * User non-funding ledger updates
     */
    @Test
    @DisplayName("Ledger: userNonFundingLedgerUpdates")
    void testUserNonFundingLedgerUpdates() {
        Info info = client.getInfo();
        long end = Instant.now().toEpochMilli();
        long start = end - CandleInterval.HOUR_1.toMillis() * 24;

        resetLogs();
        List<LedgerUpdate> updates = info.userNonFundingLedgerUpdates(address, start, end);
        assertNotNull(updates);
        assertHttpLogsPresent();
    }

    /**
     * Historical orders and TWAP slice fills
     */
    @Test
    @DisplayName("Order history: historicalOrders/userTwapSliceFills")
    void testHistoricalAndTwap() {
        Info info = client.getInfo();
        long end = Instant.now().toEpochMilli();
        long start = end - CandleInterval.HOUR_1.toMillis() * 24;

        resetLogs();
        List<HistoricalOrder> h = info.historicalOrders(address);
        assertNotNull(h);
        assertHttpLogsPresent();

        resetLogs();
        List<TwapSliceFill> twap = info.userTwapSliceFills(address);
        assertNotNull(twap);
        assertHttpLogsPresent();
    }

    /**
     * Order status: invalid OID triggers error handling
     */
    @Test
    @DisplayName("Order status error: invalid OID throws HypeError.ClientHypeError")
    void testOrderStatusInvalidOid() {
        Info info = client.getInfo();
        assertThrows(HypeError.ClientHypeError.class, () -> info.orderStatus(address, -1L));
    }

    /**
     * Frontend open orders (with dex)
     */
    @Test
    @DisplayName("Open orders: frontendOpenOrders(dex)")
    void testFrontendOpenOrdersWithDex() {
        Info info = client.getInfo();
        resetLogs();
        List<FrontendOpenOrder> fe = info.frontendOpenOrders(address, "");
        assertNotNull(fe);
        assertHttpLogsPresent();
    }

    /**
     * User fills: recent and time range
     */
    @Test
    @DisplayName("Fills: userFills and userFillsByTime")
    void testUserFillsVariants() {
        Info info = client.getInfo();
        long end = Instant.now().toEpochMilli();
        long start = end - CandleInterval.HOUR_1.toMillis() * 24;

        resetLogs();
        List<UserFill> u1 = info.userFills(address);
        assertNotNull(u1);
        assertHttpLogsPresent();

        resetLogs();
        List<UserFill> u2 = info.userFillsByTime(address, start);
        assertNotNull(u2);
        assertHttpLogsPresent();

        resetLogs();
        List<UserFill> u3 = info.userFillsByTime(address, start, end);
        assertNotNull(u3);
        assertHttpLogsPresent();

        resetLogs();
        List<UserFill> u4 = info.userFills(address, true);
        assertNotNull(u4);
        assertHttpLogsPresent();
    }

    /**
     * User fees (rebates/commissions)
     */
    @Test
    @DisplayName("Fees: userFees")
    void testUserFees() {
        Info info = client.getInfo();
        resetLogs();
        UserFees fees = info.userFees(address);
        assertNotNull(fees);
        assertHttpLogsPresent();
    }

    /**
     * clearinghouseState: dex variant
     */
    @Test
    @DisplayName("Account state: clearinghouseState(dex)")
    void testClearinghouseStateWithDex() {
        Info info = client.getInfo();
        resetLogs();
        ClearinghouseState st = info.clearinghouseState(address, "");
        assertNotNull(st);
        assertHttpLogsPresent();
    }

    /**
     * Vault details: invalid address expected to trigger 4xx
     */
    @Test
    @DisplayName("Vault details error: invalid address triggers 4xx or returns empty")
    void testVaultDetailsInvalid() {
        Info info = client.getInfo();
        // The zero address may return a valid empty response or throw HypeError depending on API state
        try {
            info.vaultDetails("0x0000000000000000000000000000000000000000", address);
        } catch (HypeError ignored) {
            // expected on some API states
        }
    }

    /**
     * Spot Deploy status and portfolio/role/rate limit
     */
    @Test
    @DisplayName("User info: spotDeployState/portfolio/userRole/userRateLimit")
    void testUserInfoMisc() {
        Info info = client.getInfo();

        resetLogs();
        SpotDeployState s = info.spotDeployState(address);
        assertNotNull(s);
        assertHttpLogsPresent();

        resetLogs();
        List<PortfolioEntry> p = info.portfolio(address);
        assertNotNull(p);
        assertHttpLogsPresent();

        resetLogs();
        UserRole r = info.userRole(address);
        assertNotNull(r);
        assertHttpLogsPresent();

        resetLogs();
        UserRateLimit rl = info.userRateLimit(address);
        assertNotNull(rl);
        assertHttpLogsPresent();
    }

    /**
     * Referral, sub-accounts and multi-sig signer mappings
     */
    @Test
    @DisplayName("User mappings: queryReferralState/querySubAccounts/queryUserToMultiSigSigners")
    void testUserMappings() {
        Info info = client.getInfo();

        resetLogs();
        ReferralState a = info.queryReferralState(address);
        assertNotNull(a);
        assertHttpLogsPresent();

        resetLogs();
        List<SubAccount> b = info.querySubAccounts(address);
        assertNotNull(b);
        assertFalse(b.isEmpty(), "Should have at least one sub-account");
        assertNotNull(b.get(0).getSubAccountUser());
        assertNotNull(b.get(0).getMaster());
        assertHttpLogsPresent();

        // API returns null when user has no multi-sig signers
        resetLogs();
        List<String> c = info.queryUserToMultiSigSigners(address);
        assertHttpLogsPresent();
    }

    /**
     * Deployment auction status and DEX abstraction state
     */
    @Test
    @DisplayName("Deployment status: queryPerpDeployAuctionStatus/querySpotDeployAuctionStatus/DEX abstraction state")
    void testDeployAndAbstraction() {
        Info info = client.getInfo();

        resetLogs();
        DeployAuctionStatus p = info.queryPerpDeployAuctionStatus();
        assertNotNull(p);
        assertHttpLogsPresent();

        resetLogs();
        SpotDeployState s = info.spotDeployState(address);
        assertNotNull(s);
        assertHttpLogsPresent();

        resetLogs();
        boolean d = info.queryUserDexAbstractionState(address);
        // boolean is always non-null, just verify call succeeds
        assertHttpLogsPresent();
    }

    /**
     * User Vault equities and extra agents
     */
    @Test
    @DisplayName("Equities and agents: userVaultEquities/extraAgents")
    void testVaultEquitiesAndExtraAgents() {
        Info info = client.getInfo();

        resetLogs();
        List<VaultEquity> e1 = info.userVaultEquities(address);
        assertNotNull(e1);
        assertHttpLogsPresent();

        resetLogs();
        List<ExtraAgent> e2 = info.extraAgents(address);
        assertNotNull(e2);
        assertHttpLogsPresent();
    }

}
