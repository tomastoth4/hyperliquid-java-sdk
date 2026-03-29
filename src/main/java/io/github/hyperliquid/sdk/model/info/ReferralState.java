package io.github.hyperliquid.sdk.model.info;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/** Referral state for a user */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReferralState {
    private ReferredBy referredBy;
    private String cumVlm;
    private String unclaimedRewards;
    private String claimedRewards;
    private String builderRewards;
    private Object referrerState;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ReferredBy {
        private String referrer;
        private String code;
    }
}
