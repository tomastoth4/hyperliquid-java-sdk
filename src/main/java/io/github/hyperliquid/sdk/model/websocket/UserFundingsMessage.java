package io.github.hyperliquid.sdk.model.websocket;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;
import java.util.List;

@Value
public class UserFundingsMessage {
    @JsonProperty("isSnapshot") boolean snapshot;
    List<WsFunding> fundings;
}
