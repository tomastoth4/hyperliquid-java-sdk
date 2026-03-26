package io.github.hyperliquid.sdk.model.info;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

/** User rate limit information */
@Value
public class UserRateLimit {
    /** Cumulative trading volume (string) */
    String cumVlm;
    /** Number of requests used */
    @JsonProperty("nRequestsUsed")
    Long nRequestsUsed;
    /** Request count limit */
    @JsonProperty("nRequestsCap")
    Long nRequestsCap;
}
