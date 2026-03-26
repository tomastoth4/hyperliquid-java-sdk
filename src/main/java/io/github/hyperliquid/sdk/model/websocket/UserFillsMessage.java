package io.github.hyperliquid.sdk.model.websocket;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.hyperliquid.sdk.model.info.UserFill;
import lombok.Value;
import java.util.List;

@Value
public class UserFillsMessage {
    @JsonProperty("isSnapshot") boolean snapshot;
    List<UserFill> fills;
}
