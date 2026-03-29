package io.github.hyperliquid.sdk.model.subscription;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class UserTwapSliceFillsSubscription extends Subscription {

    @JsonProperty("type")
    private final String type = "userTwapSliceFills";

    @JsonProperty("user")
    private final String user;

    public UserTwapSliceFillsSubscription(String user) {
        this.user = user;
    }

    public static UserTwapSliceFillsSubscription of(String user) {
        return new UserTwapSliceFillsSubscription(user);
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
