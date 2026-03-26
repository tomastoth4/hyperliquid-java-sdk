package io.github.hyperliquid.sdk.model.info;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import java.util.List;

/** Frontend open order entity wrapper (carrying trigger/take-profit/stop-loss and other additional information) */
@Value
public class FrontendOpenOrder {
    /** Currency (e.g., "BTC" or Spot index "@107") */
    String coin;
    /** Whether it is a position take-profit/stop-loss order */
    @JsonProperty("isPositionTpsl")
    Boolean isPositionTpsl;
    /** Whether it is a trigger order */
    @JsonProperty("isTrigger")
    Boolean isTrigger;
    /** Limit price (string) */
    String limitPx;
    /** Order ID */
    Long oid;
    /** Order type description */
    String orderType;
    /** Original order quantity (string) */
    String origSz;
    /** Whether to reduce position only */
    Boolean reduceOnly;
    /** Direction (A/B or Buy/Sell) */
    String side;
    /** Current remaining quantity (string) */
    String sz;
    /** Creation timestamp (milliseconds) */
    Long timestamp;
    /** Trigger condition (cross above/cross below, etc.) */
    String triggerCondition;
    /** Trigger price (string) */
    String triggerPx;
    /** Time-in-force (nullable) */
    String tif;
    /** Sub-orders (nullable) */
    List<FrontendOpenOrder> children;
}
