package io.github.hyperliquid.sdk.model.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CancelByCloidRequest {

    /**
     * The coin to cancel the order for.
     */
    private String coin;

    /**
     * The client order ID (CLOID) of the order to cancel.
     */
    private Cloid cloid;

    public static CancelByCloidRequest of(String coin, Cloid cloid) {
        return new CancelByCloidRequest(coin, cloid);
    }
}
