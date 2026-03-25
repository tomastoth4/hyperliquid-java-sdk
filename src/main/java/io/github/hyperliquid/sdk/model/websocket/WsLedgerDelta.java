package io.github.hyperliquid.sdk.model.websocket;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import java.util.HashMap;
import java.util.Map;

public class WsLedgerDelta {
    private String type;
    private Map<String, Object> extra = new HashMap<>();

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public Map<String, Object> getExtra() { return extra; }

    @JsonAnySetter
    public void setExtra(String key, Object value) { extra.put(key, value); }
}
