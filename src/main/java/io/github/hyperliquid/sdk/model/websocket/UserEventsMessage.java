package io.github.hyperliquid.sdk.model.websocket;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.github.hyperliquid.sdk.model.info.UserFill;

import java.util.List;

@JsonDeserialize(using = UserEventsMessageDeserializer.class)
public class UserEventsMessage {
    private List<UserFill> fills;
    private WsFunding funding;
    private WsLiquidation liquidation;
    private List<WsNonUserCancel> nonUserCancels;

    public List<UserFill> getFills() { return fills; }
    public void setFills(List<UserFill> fills) { this.fills = fills; }
    public WsFunding getFunding() { return funding; }
    public void setFunding(WsFunding funding) { this.funding = funding; }
    public WsLiquidation getLiquidation() { return liquidation; }
    public void setLiquidation(WsLiquidation liquidation) { this.liquidation = liquidation; }
    public List<WsNonUserCancel> getNonUserCancels() { return nonUserCancels; }
    public void setNonUserCancels(List<WsNonUserCancel> nonUserCancels) { this.nonUserCancels = nonUserCancels; }
}
