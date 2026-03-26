package io.github.hyperliquid.sdk.model.websocket;

import lombok.Value;

@Value
public class WsFunding {
    String coin;
    String fundingRate;
    String szi;
    Long time;
    Integer nSamples;
    String usdc;
}
