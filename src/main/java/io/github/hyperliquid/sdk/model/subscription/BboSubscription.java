package io.github.hyperliquid.sdk.model.subscription;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Locale;

/**
 * Best Bid and Offer (BBO) subscription.
 * <p>
 * Subscribe to the best bid and ask prices and their quantities for specified currency, more concise data, faster updates.
 * </p>
 */
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class BboSubscription extends Subscription {

    @JsonProperty("type")
    private final String type = "bbo";

    @JsonProperty("coin")
    private String coin;

    /**
     * Construct BBO subscription (no-argument constructor, used for Jackson deserialization).
     */
    public BboSubscription() {
    }

    /**
     * Construct BBO subscription.
     *
     * @param coin currency name (e.g., "BTC", "ETH") or asset ID
     */
    public BboSubscription(String coin) {
        this.coin = coin;
    }

    /**
     * Static factory method: create BBO subscription.
     *
     * @param coin currency name (e.g., "BTC", "ETH")
     * @return BboSubscription instance
     */
    public static BboSubscription of(String coin) {
        return new BboSubscription(coin);
    }

    @Override
    public String getType() {
        return type;
    }

    public void setCoin(String coin) {
        this.coin = coin;
    }

    @Override
    public String toIdentifier() {
        if (coin == null) {
            return type;
        }
        String coinKey = coin.toLowerCase(Locale.ROOT);
        return type + ":" + coinKey;
    }
}
