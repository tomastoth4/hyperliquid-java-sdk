package io.github.hyperliquid.sdk.model.info;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Value;

@Value
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserFundingEntry {
    Long time;
    String hash;
    FundingDelta delta;

    @Value
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class FundingDelta {
        String type;
        String coin;
        String usdc;
        String szi;
        String fundingRate;
        Integer nSamples;
    }
}
