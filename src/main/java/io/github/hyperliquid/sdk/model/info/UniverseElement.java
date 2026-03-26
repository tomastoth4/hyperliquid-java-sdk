package io.github.hyperliquid.sdk.model.info;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

/** Asset element (name, precision, leverage and margin table binding) */
@Value
public class UniverseElement {

    /** Quantity precision (decimal places) */
    @JsonProperty("szDecimals")
    Integer szDecimals;

    /** Asset name (e.g., "BTC") */
    @JsonProperty("name")
    String name;

    /** Maximum leverage multiple */
    @JsonProperty("maxLeverage")
    Integer maxLeverage;

    /** Bound margin table ID */
    @JsonProperty("marginTableId")
    Integer marginTableId;
}
