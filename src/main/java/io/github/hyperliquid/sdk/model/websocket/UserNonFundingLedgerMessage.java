package io.github.hyperliquid.sdk.model.websocket;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;
import java.util.List;

@Value
public class UserNonFundingLedgerMessage {
    @JsonProperty("isSnapshot") boolean snapshot;
    List<WsLedgerUpdate> updates;
}
