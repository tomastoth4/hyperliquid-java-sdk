package io.github.hyperliquid.sdk.model.info;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import java.util.List;

/**
 * Order status return wrapper
 */
@Value
public class OrderStatus {

    /**
     * Top-level status (e.g., "ok"/"error")
     */
    String status;

    /**
     * Order details and status timestamp
     */
    Order order;

    @Value
    public static class Order {
        /**
         * Order status description (filled)
         */
        String status;

        /**
         * Order details
         */
        OrderDetail order;
        /**
         * Status update timestamp (milliseconds)
         */
        Long statusTimestamp;

        @Value
        public static class OrderDetail {
            /**
             * Currency name
             */
            String coin;
            /**
             * Direction (A/B or Buy/Sell)
             */
            String side;
            /**
             * Limit price (string)
             */
            String limitPx;
            /**
             * Order quantity (string)
             */
            String sz;
            /**
             * Order ID
             */
            Long oid;
            /**
             * Creation timestamp (milliseconds)
             */
            Long timestamp;
            /**
             * Trigger condition description
             */
            String triggerCondition;
            /**
             * Whether it is a trigger order
             */
            @JsonProperty("isTrigger")
            Boolean isTrigger;
            /**
             * Trigger price (string)
             */
            String triggerPx;
            /**
             * Child order ID list (if split/sliced)
             */
            List<OrderDetail> children;
            /**
             * Whether it is a position take-profit/stop-loss
             */
            @JsonProperty("isPositionTpsl")
            Boolean isPositionTpsl;
            /**
             * Whether to reduce position only
             */
            Boolean reduceOnly;
            /**
             * Order type description
             */
            String orderType;
            /**
             * Original order quantity (string)
             */
            String origSz;
            /**
             * TIF strategy (Gtc/Alo/Ioc)
             */
            String tif;
            /**
             * Client order ID
             */
            String cloid;
        }
    }
}
