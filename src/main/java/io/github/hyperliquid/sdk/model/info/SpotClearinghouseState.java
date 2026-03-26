package io.github.hyperliquid.sdk.model.info;

import lombok.Value;

import java.util.List;

/** Spot clearinghouse state: user token balance list */
@Value
public class SpotClearinghouseState {
    /** Balance list */
    List<Balance> balances;

    @Value
    public static class Balance {
        /** Token name or index prefix form (e.g., "@107") */
        String coin;
        /** Token integer ID */
        Integer token;
        /** Frozen/occupied quantity (string) */
        String hold;
        /** Total balance quantity (string) */
        String total;
        /** Nominal USD value (string) */
        String entryNtl;
    }
}
