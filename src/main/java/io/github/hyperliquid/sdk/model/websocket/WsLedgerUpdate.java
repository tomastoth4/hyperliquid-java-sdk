package io.github.hyperliquid.sdk.model.websocket;

import lombok.Value;

@Value
public class WsLedgerUpdate {
    WsLedgerDelta delta;
    Long time;
    String hash;
}
