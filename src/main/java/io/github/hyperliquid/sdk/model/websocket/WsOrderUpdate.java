package io.github.hyperliquid.sdk.model.websocket;

import lombok.Value;

@Value
public class WsOrderUpdate {
    WsOrder order;
    WsOrderStatus status;
    Long statusTimestamp;
}
