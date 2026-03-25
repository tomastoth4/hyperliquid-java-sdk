package io.github.hyperliquid.sdk.model.websocket;

public class WsLiquidation {
    private String user;
    private Long leveragedPosition;
    private String liquidatedNtlPos;
    private String accountValue;

    public String getUser() { return user; }
    public void setUser(String user) { this.user = user; }

    public Long getLeveragedPosition() { return leveragedPosition; }
    public void setLeveragedPosition(Long leveragedPosition) { this.leveragedPosition = leveragedPosition; }

    public String getLiquidatedNtlPos() { return liquidatedNtlPos; }
    public void setLiquidatedNtlPos(String liquidatedNtlPos) { this.liquidatedNtlPos = liquidatedNtlPos; }

    public String getAccountValue() { return accountValue; }
    public void setAccountValue(String accountValue) { this.accountValue = accountValue; }
}
