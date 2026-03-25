package io.github.hyperliquid.sdk.model.websocket;

public class WsLedgerUpdate {
    private WsLedgerDelta delta;
    private Long time;
    private String hash;

    public WsLedgerDelta getDelta() { return delta; }
    public void setDelta(WsLedgerDelta delta) { this.delta = delta; }

    public Long getTime() { return time; }
    public void setTime(Long time) { this.time = time; }

    public String getHash() { return hash; }
    public void setHash(String hash) { this.hash = hash; }
}
