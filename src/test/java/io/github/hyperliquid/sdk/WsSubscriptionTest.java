package io.github.hyperliquid.sdk;

import io.github.hyperliquid.sdk.model.subscription.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WsSubscriptionTest {

    @Test void userFillsType()     { assertEquals("userFills",     UserFillsSubscription.of("0xabc").getType()); }
    @Test void userFillsId()       { assertEquals("userFills:0xabc", UserFillsSubscription.of("0xabc").toIdentifier()); }

    @Test void userFundingsType()  { assertEquals("userFundings",  UserFundingsSubscription.of("0xabc").getType()); }
    @Test void userFundingsId()    { assertEquals("userFundings:0xabc", UserFundingsSubscription.of("0xabc").toIdentifier()); }

    @Test void userLedgerType()    { assertEquals("userNonFundingLedgerUpdates", UserNonFundingLedgerUpdatesSubscription.of("0xabc").getType()); }
    @Test void userLedgerId()      { assertEquals("userNonFundingLedgerUpdates:0xabc", UserNonFundingLedgerUpdatesSubscription.of("0xabc").toIdentifier()); }

    @Test void activeAssetCtxType(){ assertEquals("activeAssetCtx", ActiveAssetCtxSubscription.of("BTC").getType()); }
    @Test void activeAssetCtxId()  { assertEquals("activeAssetCtx:BTC", ActiveAssetCtxSubscription.of("BTC").toIdentifier()); }

    @Test void activeAssetDataType(){ assertEquals("activeAssetData", ActiveAssetDataSubscription.of("0xabc","BTC").getType()); }
    @Test void activeAssetDataId()  { assertEquals("activeAssetData:0xabc:BTC", ActiveAssetDataSubscription.of("0xabc","BTC").toIdentifier()); }

    @Test void userTwapSliceFillsType() { assertEquals("userTwapSliceFills", UserTwapSliceFillsSubscription.of("0xabc").getType()); }
    @Test void userTwapSliceFillsId()   { assertEquals("userTwapSliceFills:0xabc", UserTwapSliceFillsSubscription.of("0xabc").toIdentifier()); }
}
