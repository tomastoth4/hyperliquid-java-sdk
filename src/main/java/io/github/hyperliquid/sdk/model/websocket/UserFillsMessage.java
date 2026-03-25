package io.github.hyperliquid.sdk.model.websocket;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.hyperliquid.sdk.model.info.UserFill;
import java.util.List;

public class UserFillsMessage {
    @JsonProperty("isSnapshot") private boolean snapshot;
    private List<UserFill> fills;

    public boolean isSnapshot() { return snapshot; }
    public void setSnapshot(boolean snapshot) { this.snapshot = snapshot; }
    public List<UserFill> getFills() { return fills; }
    public void setFills(List<UserFill> fills) { this.fills = fills; }
}
