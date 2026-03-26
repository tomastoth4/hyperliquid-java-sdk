package io.github.hyperliquid.sdk.model.order;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Value;

import java.util.List;

/**
 * Represents a bulk order response.
 */
@Value
public class BulkOrder {

    /**
     * The status of the bulk order. (ok)
     */
    String status;

    /**
     * The response of the bulk order.
     */
    Response response;

    @Value
    public static class Response {

        String type;

        Data data;

        @Value
        public static class Data {
            /**
             * The statuses of the orders.
             */
            List<JsonNode> statuses;
        }
    }
}
