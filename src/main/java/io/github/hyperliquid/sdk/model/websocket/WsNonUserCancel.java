package io.github.hyperliquid.sdk.model.websocket;

public class WsNonUserCancel {
    private String coin;
    private Long oid;

    public String getCoin() { return coin; }
    public void setCoin(String coin) { this.coin = coin; }

    public Long getOid() { return oid; }
    public void setOid(Long oid) { this.oid = oid; }
}
