package io.github.hyperliquid.sdk.model.subscription;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Active Asset Context Subscription
 */
public class ActiveAssetCtxSubscription extends Subscription {

    @JsonProperty("type")
    private final String type = "activeAssetCtx";

    /**
     * asset symbol/coin name
     **/
    @JsonProperty("coin")
    private final String coin;

    public ActiveAssetCtxSubscription(String coin) {
        this.coin = coin;
    }

    public static ActiveAssetCtxSubscription of(String coin) {
        return new ActiveAssetCtxSubscription(coin);
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String toIdentifier() {
        return type + ":" + coin;
    }
}
