package io.github.hyperliquid.sdk.model.info;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Value;

/**
 * openOrders returned unexecuted order entity.
 */
@Value
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenOrder {

    /**
     * Currency name or Spot index (e.g., "BTC", "@107")
     */
    String coin;
    /**
     * Limit price, string format, e.g., "29792.0"
     */
    String limitPx;
    /**
     * Order ID
     */
    Long oid;
    /**
     * Direction string (e.g., "A"/"B", or "Buy"/"Sell", etc., may vary across platforms), keep as is
     */
    String side;
    /**
     * Order quantity, string format
     */
    String sz;
    /**
     * Creation timestamp (milliseconds)
     */
    Long timestamp;
    /**
     * Client order ID (nullable)
     */
    String cloid;
}
