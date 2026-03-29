package io.github.hyperliquid.sdk.model.info;

import lombok.Value;

/**
 * Retrieve a user's fills
 * User recent trades
 **/
@Value
public class UserFill {

    /** Currency (e.g., "BTC" or Spot index "@107") */
    String coin;
    /** Execution price (string) */
    String px;
    /** Execution quantity (string) */
    String sz;
    /** Direction (A/B or Buy/Sell) */
    String side;
    /** Execution timestamp (milliseconds) */
    Long time;
    /** Starting position size at execution (string) */
    String startPosition;
    /** Direction description (e.g., open/close, etc.) */
    String dir;
    /** Closed profit and loss (string) */
    String closedPnl;
    /** Execution hash */
    String hash;
    /** Order ID */
    Long oid;
    /** Whether it is a crossed execution */
    Boolean crossed;
    /** Fee (string) */
    String fee;
    /** Execution sequence number (tid) */
    Long tid;
    /** Fee token identifier */
    String feeToken;
    /** TWAP strategy ID (if sliced execution) */
    String twapId;
    /** Builder fee (string, if applicable) */
    String builderFee;
    /** Client order ID (0x-prefixed hex, null if not set by the order placer) */
    String cloid;

    // Utility method - determine if it is a spot trade
    public boolean isSpotTrade() {
        return coin != null && coin.startsWith("@");
    }

    // Utility method - determine if it is a perpetual contract trade
    public boolean isPerpTrade() {
        return coin != null && !coin.startsWith("@");
    }

    // Utility method - get asset ID (if it is a spot trade)
    public String getAssetId() {
        if (isSpotTrade() && coin != null) {
            return coin.substring(1); // Remove "@" symbol
        }
        return coin;
    }
}
