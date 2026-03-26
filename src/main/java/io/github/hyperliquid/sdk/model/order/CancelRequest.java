package io.github.hyperliquid.sdk.model.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CancelRequest {

    /**
     * The coin to cancel the order for.
     */
    private String coin;

    /**
     * The order id to cancel.
     */
    private Long oid;

    public static CancelRequest of(String coin, Long oid) {
        return new CancelRequest(coin, oid);
    }
}
