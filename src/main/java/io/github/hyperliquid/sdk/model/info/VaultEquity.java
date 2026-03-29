package io.github.hyperliquid.sdk.model.info;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Value;

@Value
@JsonIgnoreProperties(ignoreUnknown = true)
public class VaultEquity {
    String vaultAddress;
    String equity;
    Long lockedUntilTimestamp;
}
