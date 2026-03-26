package io.github.hyperliquid.sdk.model.order;

import lombok.Value;

import java.util.List;

/**
 * Order response encapsulation (contains resting/filled/error status)
 */
@Value
public class Order {

    /**
     * Top-level status (e.g., "ok"/"error")
     */
    String status;

    /**
     * Response body, contains type and data
     */
    Response response;

    @Value
    public static class Resting {
        /**
         * Resting order ID
         */
        long oid;

        /**
         * Client order ID
         */
        String cloid;
    }

    @Value
    public static class Statuses {
        /**
         * Unfilled resting order information
         */
        Resting resting;
        /**
         * Filled order information
         */
        Filled filled;
        /**
         * Error description (if any)
         */
        String error;
    }

    @Value
    public static class Filled {
        /**
         * Total filled quantity (string)
         */
        String totalSz;
        /**
         * Average filled price (string)
         */
        String avgPx;
        /**
         * Order ID
         */
        Long oid;
        /**
         * Client order ID
         */
        String cloid;
    }

    @Value
    public static class Data {
        /**
         * List of order statuses
         */
        List<Statuses> statuses;
    }

    @Value
    public static class Response {
        /**
         * Response type (e.g., "order")
         */
        String type;
        /**
         * Order status data
         */
        Data data;
    }
}
