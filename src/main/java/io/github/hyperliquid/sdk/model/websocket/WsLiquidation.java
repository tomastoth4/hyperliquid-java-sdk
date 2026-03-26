package io.github.hyperliquid.sdk.model.websocket;

import lombok.Value;

@Value
public class WsLiquidation {
    String user;
    Long leveragedPosition;
    String liquidatedNtlPos;
    String accountValue;
}
