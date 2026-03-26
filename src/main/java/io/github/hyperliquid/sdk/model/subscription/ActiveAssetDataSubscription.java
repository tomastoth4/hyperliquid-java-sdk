package io.github.hyperliquid.sdk.model.subscription;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Active Asset Data Subscription
 */
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ActiveAssetDataSubscription extends Subscription {

    @JsonProperty("type")
    private final String type = "activeAssetData";

    /**
     * wallet address
     **/
    @JsonProperty("user")
    private final String user;

    /**
     * asset symbol/coin name
     **/
    @JsonProperty("coin")
    private final String coin;

    public ActiveAssetDataSubscription(String user, String coin) {
        this.user = user;
        this.coin = coin;
    }

    public static ActiveAssetDataSubscription of(String user, String coin) {
        return new ActiveAssetDataSubscription(user, coin);
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String toIdentifier() {
        return type + ":" + user + ":" + coin;
    }
}
