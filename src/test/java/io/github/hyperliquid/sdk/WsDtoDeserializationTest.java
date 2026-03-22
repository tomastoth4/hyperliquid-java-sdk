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
}
