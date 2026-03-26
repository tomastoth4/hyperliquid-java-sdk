package io.github.hyperliquid.sdk.model.websocket;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import lombok.Data;
import java.util.HashMap;
import java.util.Map;

@Data
public class WsLedgerDelta {
    private String type;
    private Map<String, Object> extra = new HashMap<>();

    @JsonAnySetter
    public void setExtra(String key, Object value) { extra.put(key, value); }
}
