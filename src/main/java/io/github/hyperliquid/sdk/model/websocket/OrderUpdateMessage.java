package io.github.hyperliquid.sdk.model.websocket;

import java.util.List;

public class OrderUpdateMessage {
    private List<WsOrderUpdate> orders;

    public List<WsOrderUpdate> getOrders() { return orders; }
    public void setOrders(List<WsOrderUpdate> orders) { this.orders = orders; }
}
