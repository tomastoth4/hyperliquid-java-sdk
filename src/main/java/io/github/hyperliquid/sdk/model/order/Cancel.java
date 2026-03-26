package io.github.hyperliquid.sdk.model.order;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Value;

import java.util.List;

@Value
public class Cancel {

    /**
     * The status of the request.
     */
    String status;

    /**
     * The response of the request.
     */
    Response response;

    @Value
    public static class Response {

        /**
         * The type of the response. "cancel"
         */
        String type;

        Data data;

        @Value
        public static class Data {

            List<JsonNode> statuses;
        }
    }
}
