package io.github.hyperliquid.sdk.model.order;

import lombok.Value;

@Value
public class TwapOrderResult {
    Long twapId;
    String coin;
    String sz;
    Boolean isBuy;
    Integer minutes;
    Boolean reduceOnly;
}
