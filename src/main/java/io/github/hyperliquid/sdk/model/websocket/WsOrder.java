package io.github.hyperliquid.sdk.model.websocket;

import lombok.Value;

@Value
public class WsOrder {
    String coin;
    String limitPx;
    Long oid;
    String side;
    String sz;
    Long timestamp;
    String cloid;
    String origSz;
    Boolean reduceOnly;
    String orderType;
    String tif;
}
