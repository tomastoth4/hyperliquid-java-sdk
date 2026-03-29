package io.github.hyperliquid.sdk;

import io.github.hyperliquid.sdk.model.info.UserFill;
import io.github.hyperliquid.sdk.utils.JSONUtil;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserFillCloidTest {

    @Test
    void userFillDeserializesWithCloid() throws Exception {
        String json = """
            {"coin":"ETH","px":"2000","sz":"0.1","side":"A","time":1700000000,
             "startPosition":"0","dir":"Open Long","closedPnl":"0","hash":"0xabc",
             "oid":1,"crossed":false,"fee":"0.1","tid":1,"feeToken":"USDC",
             "twapId":null,"builderFee":null,"cloid":"0x1234abcd"}
            """;
        UserFill fill = JSONUtil.readValue(json, UserFill.class);
        assertEquals("0x1234abcd", fill.getCloid());
    }

    @Test
    void userFillDeserializesWithoutCloid() throws Exception {
        String json = """
            {"coin":"ETH","px":"2000","sz":"0.1","side":"A","time":1700000000,
             "startPosition":"0","dir":"Open Long","closedPnl":"0","hash":"0xabc",
             "oid":1,"crossed":false,"fee":"0.1","tid":1,"feeToken":"USDC"}
            """;
        UserFill fill = JSONUtil.readValue(json, UserFill.class);
        assertNull(fill.getCloid());
    }
}
