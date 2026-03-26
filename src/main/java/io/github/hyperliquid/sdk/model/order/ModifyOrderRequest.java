package io.github.hyperliquid.sdk.model.order;

import lombok.Data;

/**
 * Order modification request wrapper.
 * <p>
 * Used for batch order modification, supports locating orders via OID or Cloid.
 * </p>
 */
@Data
public class ModifyOrderRequest {

    /**
     * Order ID (OID)
     */
    private Long oid;

    /**
     * Currency name (e.g., "ETH", "BTC").
     */
    private String coin;

    /**
     * Whether to buy (true=buy/long, false=sell/short).
     * <p>
     * Can be empty for market close scenarios, inferred by business layer.
     * </p>
     */
    private Boolean isBuy;

    /**
     * Order quantity (string).
     * <p>
     * Use string representation to avoid floating-point precision issues.
     * Examples: "0.1", "0.123456789"
     * </p>
     */
    private String sz;

    /**
     * Limit price (string).
     * <p>
     * - Can be empty (market order or trigger order market execution)
     * - Use string representation to avoid floating-point precision issues
     * - Examples: "3500.0", "3500.123456"
     * </p>
     */
    private String limitPx;

    /**
     * Order type: limit (TIF) or trigger (triggerPx/isMarket/tpsl).
     * <p>
     * Can be empty to represent default limit/market behavior.
     * </p>
     */
    private OrderType orderType;

    /**
     * Reduce-only flag (true means will not increase position).
     * <p>
     * Used for closing positions or trigger reductions to prevent reverse opening.
     * </p>
     */
    private Boolean reduceOnly;

    /**
     * Client order ID (Cloid), can be empty.
     * <p>
     * Used for idempotency and subsequent cancellation operations.
     * </p>
     */
    private Cloid cloid;

    public static ModifyOrderRequest byOid(String coin, Long oid) {
        ModifyOrderRequest modifyOrderRequest = new ModifyOrderRequest();
        modifyOrderRequest.setOid(oid);
        modifyOrderRequest.setCoin(coin);
        return modifyOrderRequest;
    }

    public void setOrderType(TriggerOrderType trigger) {
        this.orderType = new OrderType(trigger);
    }

    public void setOrderType(LimitOrderType limit) {
        this.orderType = new OrderType(limit);
    }

    public Boolean isBuy() {
        return isBuy;
    }
}
