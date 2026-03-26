package io.github.hyperliquid.sdk.model.websocket;

import lombok.Value;

@Value
public class WsTrade {
    String coin;
    String side;
    String px;
    String sz;
    Long time;
    String hash;
    Long tid;
}
