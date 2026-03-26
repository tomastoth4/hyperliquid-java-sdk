package io.github.hyperliquid.sdk.model.info;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

/** Margin tier (position lower bound and maximum leverage) */
@Value
public class MarginTier {

    /** Position size lower bound (string) */
    @JsonProperty("lowerBound")
    String lowerBound;

    /** Corresponding maximum leverage multiple */
    @JsonProperty("maxLeverage")
    Integer maxLeverage;
}
