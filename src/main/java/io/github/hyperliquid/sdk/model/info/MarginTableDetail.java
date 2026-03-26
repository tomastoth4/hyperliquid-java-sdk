package io.github.hyperliquid.sdk.model.info;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import java.util.List;

/** Margin table details (description and margin tiers) */
@Value
public class MarginTableDetail {

    /** Description information */
    @JsonProperty("description")
    String description;

    /** Margin tier list */
    @JsonProperty("marginTiers")
    List<MarginTier> marginTiers;
}
