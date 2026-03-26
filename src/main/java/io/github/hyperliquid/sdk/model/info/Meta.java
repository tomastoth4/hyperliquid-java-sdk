package io.github.hyperliquid.sdk.model.info;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import java.util.List;

/** Market metadata (perpetual) */
@Value
public class Meta {

    /** List of supported trading assets */
    @JsonProperty("universe")
    List<Universe> universe;

    /** Integer ID of collateral token */
    @JsonProperty("collateralToken")
    Integer collateralToken;

    /** Margin table collection (raw server structure) */
    @JsonProperty("marginTables")
    List<List<Object>> marginTables;

    @Value
    public static class Universe {
        /** Quantity precision (decimal places) */
        @JsonProperty("szDecimals")
        Integer szDecimals;

        /** Asset name (e.g., "BTC") */
        @JsonProperty("name")
        String name;

        /** Maximum leverage for this asset */
        @JsonProperty("maxLeverage")
        Integer maxLeverage;

        /** Corresponding margin table ID */
        @JsonProperty("marginTableId")
        Integer marginTableId;
    }
}
