package io.github.hyperliquid.sdk.model.subscription;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * User Fundings Subscription
 */
public class UserFundingsSubscription extends Subscription {

    @JsonProperty("type")
    private final String type = "userFundings";

    /**
     * wallet address
     **/
    @JsonProperty("user")
    private final String user;

    public UserFundingsSubscription(String user) {
        this.user = user;
    }

    public static UserFundingsSubscription of(String user) {
        return new UserFundingsSubscription(user);
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String toIdentifier() {
        return type + ":" + user;
    }
}
