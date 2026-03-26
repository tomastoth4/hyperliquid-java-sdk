package io.github.hyperliquid.sdk.model.info;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

/**
 * Candle (K-line) type model.
 */
@Value
public class Candle {

    /**
     * End timestamp (milliseconds)
     */
    @JsonProperty("T")
    Long endTimestamp;

    /**
     * Start timestamp (milliseconds)
     */
    @JsonProperty("t")
    Long startTimestamp;

    /**
     * Closing price
     */
    @JsonProperty("c")
    String closePrice;

    /**
     * Highest price
     */
    @JsonProperty("h")
    String highPrice;

    /**
     * Lowest price
     */
    @JsonProperty("l")
    String lowPrice;

    /**
     * Opening price
     */
    @JsonProperty("o")
    String openPrice;

    /**
     * Trading volume
     */
    @JsonProperty("v")
    String volume;

    /**
     * Time interval (e.g., "1m", "15m", "1h", "1d", etc.)
     */
    @JsonProperty("i")
    String interval;

    /**
     * Trading pair symbol (e.g., "BTC")
     */
    @JsonProperty("s")
    String symbol;

    /**
     * Number of trades
     */
    @JsonProperty("n")
    Integer tradeCount;
}
