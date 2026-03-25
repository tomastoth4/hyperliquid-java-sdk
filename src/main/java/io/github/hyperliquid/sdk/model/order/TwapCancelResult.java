package io.github.hyperliquid.sdk.model.order;

public class TwapCancelResult {
    private Long twapId;
    private String status;

    public Long getTwapId() { return twapId; }
    public void setTwapId(Long twapId) { this.twapId = twapId; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
