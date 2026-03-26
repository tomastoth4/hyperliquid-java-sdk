package io.github.hyperliquid.sdk.model.info;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import java.util.List;

/** Asset context (perpetual) indicator collection */
@Value
public class AssetCtx {

    /** Current funding rate (string decimal) */
    @JsonProperty("funding")
    String funding;

    /** Open interest (nominal USD scale, string) */
    @JsonProperty("openInterest")
    String openInterest;

    /** Previous day closing price (string) */
    @JsonProperty("prevDayPx")
    String prevDayPx;

    /** Daily nominal trading volume (USD, string) */
    @JsonProperty("dayNtlVlm")
    String dayNtlVlm;

    /** Perpetual premium (string) */
    @JsonProperty("premium")
    String premium;

    /** Oracle price (string) */
    @JsonProperty("oraclePx")
    String oraclePx;

    /** Mark price (string) */
    @JsonProperty("markPx")
    String markPx;

    /** Mid price (buy/sell mid price, may be null) */
    @JsonProperty("midPx")
    String midPx;

    /** Impact prices (estimated buy/sell direction execution impact prices, length is 2) */
    @JsonProperty("impactPxs")
    List<String> impactPxs;

    /** Daily base quantity trading volume (coin quantity, string) */
    @JsonProperty("dayBaseVlm")
    String dayBaseVlm;
}
