package io.github.hyperliquid.sdk.model.info;

import lombok.Value;

/***
 * Funding rate history.
 **/
@Value
public class FundingHistory {

    /**
     * Currency name
     **/
    String coin;

    /***
     * Funding rate
     **/
    String fundingRate;

    /***
     * Premium rate
     **/
    String premium;

    /***
     * Timestamp (milliseconds)
     **/
    Long time;
}
