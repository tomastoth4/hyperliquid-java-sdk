package io.github.hyperliquid.sdk.model.info;

import lombok.Value;

import java.util.List;

/**
 * L2 order book snapshot (top 10 bid/ask levels)
 */
@Value
public class L2Book {

    /**
     * Currency name (e.g., "BTC")
     */
    String coin;
    /**
     * Snapshot timestamp (milliseconds)
     */
    Long time;
    /**
     * Bid/ask list: index 0 for bids, index 1 for asks
     */
    List<List<Levels>> levels;

    @Value
    public static class Levels {
        /**
         * Price at this level (string)
         */
        String px;
        /**
         * Total order quantity at this level (string)
         */
        String sz;
        /**
         * Number of orders/level count at this price
         */
        Integer n;
    }
}
