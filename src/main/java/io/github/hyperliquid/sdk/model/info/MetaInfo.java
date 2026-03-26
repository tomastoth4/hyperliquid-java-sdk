package io.github.hyperliquid.sdk.model.info;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import java.util.List;

/** Market metadata (typed) */
@Value
public class MetaInfo {
    /** List of supported trading assets */
    @JsonProperty("universe")
    List<UniverseElement> universe;

    /** Margin table collection (typed) */
    @JsonProperty("marginTables")
    List<MarginTableEntry> marginTables;

    /** Integer ID of collateral token */
    @JsonProperty("collateralToken")
    Integer collateralToken;
}
