package io.github.hyperliquid.sdk.model.subscription;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * User Non-Funding Ledger Updates Subscription
 */
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class UserNonFundingLedgerUpdatesSubscription extends Subscription {

    @JsonProperty("type")
    private final String type = "userNonFundingLedgerUpdates";

    /**
     * wallet address
     **/
    @JsonProperty("user")
    private final String user;

    public UserNonFundingLedgerUpdatesSubscription(String user) {
        this.user = user;
    }

    public static UserNonFundingLedgerUpdatesSubscription of(String user) {
        return new UserNonFundingLedgerUpdatesSubscription(user);
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
