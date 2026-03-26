package io.github.hyperliquid.sdk.model.order;

import lombok.Value;

@Value
public class TwapCancelResult {
    Long twapId;
    String status;
}
