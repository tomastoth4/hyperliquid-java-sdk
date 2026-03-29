package io.github.hyperliquid.sdk.model.info;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Value;

/** Spot asset context (market data for a spot asset) */
@Value
@JsonIgnoreProperties(ignoreUnknown = true)
public class SpotAssetCtx {
    String prevDayPx;
    String dayNtlVlm;
    String markPx;
    String midPx;
    String circulatingSupply;
    String coin;
}
