package io.github.hyperliquid.sdk.model.websocket;

public class WsFunding {
    private String coin;
    private String fundingRate;
    private String szi;
    private Long time;
    private Integer nSamples;
    private String usdc;

    public String getCoin() { return coin; }
    public void setCoin(String coin) { this.coin = coin; }

    public String getFundingRate() { return fundingRate; }
    public void setFundingRate(String fundingRate) { this.fundingRate = fundingRate; }

    public String getSzi() { return szi; }
    public void setSzi(String szi) { this.szi = szi; }

    public Long getTime() { return time; }
    public void setTime(Long time) { this.time = time; }

    public Integer getNSamples() { return nSamples; }
    public void setNSamples(Integer nSamples) { this.nSamples = nSamples; }

    public String getUsdc() { return usdc; }
    public void setUsdc(String usdc) { this.usdc = usdc; }
}
