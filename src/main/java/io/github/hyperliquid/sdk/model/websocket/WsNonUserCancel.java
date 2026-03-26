package io.github.hyperliquid.sdk.model.websocket;

import lombok.Value;

@Value
public class WsNonUserCancel {
    String coin;
    Long oid;
}
