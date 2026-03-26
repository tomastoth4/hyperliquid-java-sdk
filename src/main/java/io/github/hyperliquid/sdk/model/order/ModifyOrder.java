package io.github.hyperliquid.sdk.model.order;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Value;

@Value
public class ModifyOrder {
    String status;
    JsonNode response;
}
