package io.github.hyperliquid.sdk.model.websocket;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import java.util.HashMap;
import java.util.Map;

public class ActiveAssetDataMessage {
    private String user;
    private String coin;
    private String leverageType;
    private String leverage;
    private String[] maxTradeSzs;
    private String[] availableToTrade;
    private Map<String, Object> extra = new HashMap<>();

    public String getUser() { return user; }
    public void setUser(String user) { this.user = user; }
    public String getCoin() { return coin; }
    public void setCoin(String coin) { this.coin = coin; }
    public String getLeverageType() { return leverageType; }
    public void setLeverageType(String leverageType) { this.leverageType = leverageType; }
    public String getLeverage() { return leverage; }
    public void setLeverage(String leverage) { this.leverage = leverage; }
    public String[] getMaxTradeSzs() { return maxTradeSzs; }
    public void setMaxTradeSzs(String[] maxTradeSzs) { this.maxTradeSzs = maxTradeSzs; }
    public String[] getAvailableToTrade() { return availableToTrade; }
    public void setAvailableToTrade(String[] availableToTrade) { this.availableToTrade = availableToTrade; }
    public Map<String, Object> getExtra() { return extra; }

    @JsonAnySetter
    public void setExtra(String key, Object value) { extra.put(key, value); }
}
