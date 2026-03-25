package io.github.hyperliquid.sdk.model.order;

public class TwapOrderResult {
    private Long twapId;
    private String coin;
    private String sz;
    private Boolean isBuy;
    private Integer minutes;
    private Boolean reduceOnly;

    public Long getTwapId() { return twapId; }
    public void setTwapId(Long twapId) { this.twapId = twapId; }
    public String getCoin() { return coin; }
    public void setCoin(String coin) { this.coin = coin; }
    public String getSz() { return sz; }
    public void setSz(String sz) { this.sz = sz; }
    public Boolean getIsBuy() { return isBuy; }
    public void setIsBuy(Boolean isBuy) { this.isBuy = isBuy; }
    public Integer getMinutes() { return minutes; }
    public void setMinutes(Integer minutes) { this.minutes = minutes; }
    public Boolean getReduceOnly() { return reduceOnly; }
    public void setReduceOnly(Boolean reduceOnly) { this.reduceOnly = reduceOnly; }
}
