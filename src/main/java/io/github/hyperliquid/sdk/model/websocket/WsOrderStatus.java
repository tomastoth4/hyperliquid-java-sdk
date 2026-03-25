package io.github.hyperliquid.sdk.model.websocket;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum WsOrderStatus {
    @JsonProperty("open")      OPEN,
    @JsonProperty("filled")    FILLED,
    @JsonProperty("canceled")  CANCELED,
    @JsonProperty("triggered") TRIGGERED
}
