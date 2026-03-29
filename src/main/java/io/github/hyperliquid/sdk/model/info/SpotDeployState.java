package io.github.hyperliquid.sdk.model.info;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/** Spot token deploy state and gas auction information */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SpotDeployState {
    private List<TokenDeployState> states;
    private DeployAuctionStatus gasAuction;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class TokenDeployState {
        private Integer token;
        private TokenSpec spec;
        private String fullName;
        private List<Integer> spots;
        private String maxSupply;
        private String hyperliquidityGenesisBalance;
        private String totalGenesisBalanceWei;
        private String deployerTradingFeeShare;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class TokenSpec {
        private String name;
        private Integer szDecimals;
        private Integer weiDecimals;
    }
}
