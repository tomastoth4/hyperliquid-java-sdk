package io.github.hyperliquid.sdk.model.subscription;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * User Fills Subscription
 */
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class UserFillsSubscription extends Subscription {

    @JsonProperty("type")
    private final String type = "userFills";

    /**
     * wallet address
     **/
    @JsonProperty("user")
    private final String user;

    public UserFillsSubscription(String user) {
        this.user = user;
    }

    public static UserFillsSubscription of(String user) {
        return new UserFillsSubscription(user);
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
