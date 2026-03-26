package io.github.hyperliquid.sdk.model.websocket;

import lombok.Value;
import java.util.List;

@Value
public class OrderUpdateMessage {
    List<WsOrderUpdate> orders;
}
