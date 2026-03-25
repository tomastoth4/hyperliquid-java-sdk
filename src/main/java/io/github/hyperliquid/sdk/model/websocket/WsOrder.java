package io.github.hyperliquid.sdk.model.websocket;

public class WsOrder {
    private String coin;
    private String limitPx;
    private Long oid;
    private String side;
    private String sz;
    private Long timestamp;
    private String cloid;
    private String origSz;
    private Boolean reduceOnly;
    private String orderType;
    private String tif;

    public String getCoin() { return coin; }
    public void setCoin(String coin) { this.coin = coin; }

    public String getLimitPx() { return limitPx; }
    public void setLimitPx(String limitPx) { this.limitPx = limitPx; }

    public Long getOid() { return oid; }
    public void setOid(Long oid) { this.oid = oid; }

    public String getSide() { return side; }
    public void setSide(String side) { this.side = side; }

    public String getSz() { return sz; }
    public void setSz(String sz) { this.sz = sz; }

    public Long getTimestamp() { return timestamp; }
    public void setTimestamp(Long timestamp) { this.timestamp = timestamp; }

    public String getCloid() { return cloid; }
    public void setCloid(String cloid) { this.cloid = cloid; }

    public String getOrigSz() { return origSz; }
    public void setOrigSz(String origSz) { this.origSz = origSz; }

    public Boolean getReduceOnly() { return reduceOnly; }
    public void setReduceOnly(Boolean reduceOnly) { this.reduceOnly = reduceOnly; }

    public String getOrderType() { return orderType; }
    public void setOrderType(String orderType) { this.orderType = orderType; }

    public String getTif() { return tif; }
    public void setTif(String tif) { this.tif = tif; }
}
