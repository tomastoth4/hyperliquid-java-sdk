package io.github.hyperliquid.sdk.model.info;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/** User fee schedule and volume information */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserFees {
    private List<DailyUserVlm> dailyUserVlm;
    private FeeSchedule feeSchedule;
    private String userCrossRate;
    private String userAddRate;
    private String userSpotCrossRate;
    private String userSpotAddRate;
    private String activeReferralDiscount;
    private ActiveStakingDiscount activeStakingDiscount;
    private Object trial;
    private String feeTrialReward;
    private Long nextTrialAvailableTimestamp;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DailyUserVlm {
        private String date;
        private String userCross;
        private String userAdd;
        private String exchange;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class FeeSchedule {
        private String cross;
        private String add;
        private String spotCross;
        private String spotAdd;
        private FeeScheduleTiers tiers;
        private String referralDiscount;
        private List<StakingDiscountTier> stakingDiscountTiers;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class FeeScheduleTiers {
        private List<VipTier> vip;
        private List<MmTier> mm;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class VipTier {
        private String ntlCutoff;
        private String cross;
        private String add;
        private String spotCross;
        private String spotAdd;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class MmTier {
        private String makerFractionCutoff;
        private String add;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class StakingDiscountTier {
        private String bpsOfMaxSupply;
        private String discount;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ActiveStakingDiscount {
        private String bpsOfMaxSupply;
        private String discount;
    }
}
