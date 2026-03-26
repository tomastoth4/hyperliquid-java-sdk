package io.github.hyperliquid.sdk.model.info;

import lombok.Value;

/** Update leverage operation return */
@Value
public class UpdateLeverage {
    /** Top-level status (e.g., "ok"/"error") */
    String status;
    /** Response body (type, etc.) */
    Response response;

    @Value
    public static class Response {
        /** Response type description */
        String type;
    }
}
