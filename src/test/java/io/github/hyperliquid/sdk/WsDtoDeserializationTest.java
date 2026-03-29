package io.github.hyperliquid.sdk;

import io.github.hyperliquid.sdk.model.websocket.*;
import io.github.hyperliquid.sdk.utils.JSONUtil;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WsDtoDeserializationTest {

    @Test
    void wsOrderUpdateDeserializes() throws Exception {
        String json = """
            {"order":{"coin":"ETH","limitPx":"2000","oid":123,"side":"A","sz":"0.1",
             "timestamp":1700000000,"cloid":null,"origSz":"0.1","reduceOnly":false,
             "orderType":"Limit","tif":"Gtc"},
             "status":"open","statusTimestamp":1700000001}
            """;
        WsOrderUpdate update = JSONUtil.readValue(json, WsOrderUpdate.class);
        assertNotNull(update);
        assertEquals(WsOrderStatus.OPEN, update.getStatus());
        assertNotNull(update.getOrder());
        assertEquals("ETH", update.getOrder().getCoin());
        assertEquals(123L, update.getOrder().getOid());
    }

    @Test
    void wsTradeDeserializes() throws Exception {
        String json = """
            {"coin":"BTC","side":"B","px":"50000","sz":"0.01","time":1700000000,
             "hash":"0xabc","tid":999}
            """;
        WsTrade trade = JSONUtil.readValue(json, WsTrade.class);
        assertEquals("BTC", trade.getCoin());
        assertEquals(999L, trade.getTid());
    }

    @Test
    void wsFundingDeserializes() throws Exception {
        String json = """
            {"coin":"ETH","fundingRate":"0.0001","szi":"1.5","time":1700000000,
             "nSamples":8,"usdc":"0.15"}
            """;
        WsFunding f = JSONUtil.readValue(json, WsFunding.class);
        assertEquals("ETH", f.getCoin());
        assertEquals("0.0001", f.getFundingRate());
    }

    @Test
    void wsLedgerUpdateDeserializes() throws Exception {
        String json = """
            {"delta":{"type":"deposit","amount":"100","nonce":1},"time":1700000000,"hash":"0xdef"}
            """;
        WsLedgerUpdate upd = JSONUtil.readValue(json, WsLedgerUpdate.class);
        assertEquals("deposit", upd.getDelta().getType());
        assertEquals(1700000000L, upd.getTime());
    }

    @Test
    void wsOrderUpdateDeserializesFromElement() throws Exception {
        String json = """
            {"order":{"coin":"BTC","limitPx":"50000","oid":42,"side":"B","sz":"0.01",
               "timestamp":1700000000,"cloid":null,"origSz":"0.01","reduceOnly":false,
               "orderType":"Limit","tif":"Gtc"},
              "status":"filled","statusTimestamp":1700000002}
            """;
        WsOrderUpdate update = JSONUtil.readValue(json, WsOrderUpdate.class);
        assertEquals(WsOrderStatus.FILLED, update.getStatus());
        assertEquals(42L, update.getOrder().getOid());
    }

    @Test
    void userFillsMessageDeserializes() throws Exception {
        String json = """
            {"isSnapshot":true,"fills":[
              {"coin":"ETH","px":"2000","sz":"0.1","side":"A","time":1700000000,
               "startPosition":"0","dir":"Open Long","closedPnl":"0","hash":"0xabc",
               "oid":1,"crossed":false,"fee":"0.1","tid":1,"feeToken":"USDC"}
            ]}
            """;
        io.github.hyperliquid.sdk.model.websocket.UserFillsMessage msg =
            JSONUtil.readValue(json, io.github.hyperliquid.sdk.model.websocket.UserFillsMessage.class);
        assertTrue(msg.isSnapshot());
        assertEquals(1, msg.getFills().size());
        assertEquals("ETH", msg.getFills().get(0).getCoin());
    }

    @Test
    void userEventsMessageFillsVariant() throws Exception {
        String json = """
            {"fills":[
              {"coin":"ETH","px":"2000","sz":"0.1","side":"A","time":1700000000,
               "startPosition":"0","dir":"Open Long","closedPnl":"0","hash":"0xabc",
               "oid":1,"crossed":false,"fee":"0.1","tid":1,"feeToken":"USDC"}
            ]}
            """;
        io.github.hyperliquid.sdk.model.websocket.UserEventsMessage msg =
            JSONUtil.readValue(json, io.github.hyperliquid.sdk.model.websocket.UserEventsMessage.class);
        assertNotNull(msg.getFills());
        assertNull(msg.getFunding());
        assertNull(msg.getLiquidation());
        assertEquals(1, msg.getFills().size());
    }

    @Test
    void userEventsMessageLiquidationVariant() throws Exception {
        String json = """
            {"liquidation":{"user":"0xabc","leveragedPosition":1,"liquidatedNtlPos":"500","accountValue":"0"}}
            """;
        io.github.hyperliquid.sdk.model.websocket.UserEventsMessage msg =
            JSONUtil.readValue(json, io.github.hyperliquid.sdk.model.websocket.UserEventsMessage.class);
        assertNull(msg.getFills());
        assertNotNull(msg.getLiquidation());
        assertEquals("0xabc", msg.getLiquidation().getUser());
    }

    @Test
    void userTwapSliceFillsMessageDeserializes() throws Exception {
        String json = """
            {"isSnapshot":false,"fills":[
              {"coin":"BTC","px":"65000","sz":"0.01","side":"B","time":1700000000,
               "startPosition":"0","dir":"Open Long","closedPnl":"0",
               "hash":"0x0000000000000000000000000000000000000000000000000000000000000000",
               "oid":1,"crossed":true,"fee":"0.1","tid":1,"feeToken":"USDC",
               "twapId":"12345","builderFee":null,"cloid":null}
            ]}
            """;
        UserTwapSliceFillsMessage msg =
            JSONUtil.readValue(json, UserTwapSliceFillsMessage.class);
        assertFalse(msg.isSnapshot());
        assertEquals(1, msg.getFills().size());
        assertEquals("BTC", msg.getFills().get(0).getCoin());
        assertEquals("12345", msg.getFills().get(0).getTwapId());
        assertNull(msg.getFills().get(0).getCloid());
    }
}
