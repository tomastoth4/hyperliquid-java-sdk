package io.github.hyperliquid.sdk.model.info;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Value;

/** Sub-account state including clearinghouse and spot state */
@Value
@JsonIgnoreProperties(ignoreUnknown = true)
public class SubAccount {
    String subAccountUser;
    String name;
    String master;
    ClearinghouseState clearinghouseState;
    SpotClearinghouseState spotState;
}
