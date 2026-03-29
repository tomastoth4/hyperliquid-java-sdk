package io.github.hyperliquid.sdk.model.websocket;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.hyperliquid.sdk.model.info.UserFill;
import lombok.Value;
import java.util.List;

@Value
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserTwapSliceFillsMessage {
    @JsonProperty("isSnapshot") boolean snapshot;
    List<UserFill> fills;
}
