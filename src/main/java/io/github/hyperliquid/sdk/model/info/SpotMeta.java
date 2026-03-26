package io.github.hyperliquid.sdk.model.info;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import java.util.List;

/** Spot market metadata (asset and token information) */
@Value
public class SpotMeta {
    /** Spot assets (aggregated tokens) collection */
    List<Universe> universe;
    /** Spot tokens list */
    List<Token> tokens;

    @Value
    public static class Universe {
        /** List of token IDs contained in this spot asset */
        List<Integer> tokens;
        /** Asset abbreviation (e.g., "BTC") */
        String name;
        /** Asset index (integer) */
        int index;
        /** Whether it is a canonical main asset */
        @JsonProperty("isCanonical")
        boolean isCanonical;
    }

    @Value
    public static class Token {
        /** Token name (e.g., "WETH") */
        String name;
        /** Trading quantity precision */
        Integer szDecimals;
        /** Wei precision (EVM token smallest unit precision) */
        Integer weiDecimals;
        /** Token index (integer) */
        Integer index;
        /** Token unique ID (string) */
        String tokenId;
        /** Whether it is a canonical main token */
        @JsonProperty("isCanonical")
        Boolean isCanonical;
        /** EVM contract information (may be null) */
        EvmContract evmContract;
        /** Token full name (may be null) */
        String fullName;
        /** Deployer trading fee share ratio (string, may be null) */
        String deployerTradingFeeShare;

        @Value
        public static class EvmContract {
            /** Contract address */
            String address;
            @JsonProperty("evm_extra_wei_decimals")
            int evmExtraWeiDecimals; /** Additional Wei precision (contract feature) */
        }
    }
}
