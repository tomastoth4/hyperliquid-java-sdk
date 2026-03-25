package io.github.hyperliquid.sdk.model.websocket;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class UserNonFundingLedgerMessage {
    @JsonProperty("isSnapshot") private boolean snapshot;
    private List<WsLedgerUpdate> updates;

    public boolean isSnapshot() { return snapshot; }
    public void setSnapshot(boolean snapshot) { this.snapshot = snapshot; }
    public List<WsLedgerUpdate> getUpdates() { return updates; }
    public void setUpdates(List<WsLedgerUpdate> updates) { this.updates = updates; }
}
