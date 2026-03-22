package io.github.hyperliquid.sdk.model.websocket;

public class WsOrderUpdate {
    private WsOrder order;
    private WsOrderStatus status;
    private Long statusTimestamp;

    public WsOrder getOrder() { return order; }
    public void setOrder(WsOrder order) { this.order = order; }

    public WsOrderStatus getStatus() { return status; }
    public void setStatus(WsOrderStatus status) { this.status = status; }

    public Long getStatusTimestamp() { return statusTimestamp; }
    public void setStatusTimestamp(Long statusTimestamp) { this.statusTimestamp = statusTimestamp; }
}
