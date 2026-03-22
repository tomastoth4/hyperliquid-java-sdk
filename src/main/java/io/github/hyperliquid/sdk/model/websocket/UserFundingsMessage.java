package io.github.hyperliquid.sdk.model.websocket;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class UserFundingsMessage {
    @JsonProperty("isSnapshot") private boolean snapshot;
    private List<WsFunding> fundings;

    public boolean isSnapshot() { return snapshot; }
    public void setSnapshot(boolean snapshot) { this.snapshot = snapshot; }
    public List<WsFunding> getFundings() { return fundings; }
    public void setFundings(List<WsFunding> fundings) { this.fundings = fundings; }
}
