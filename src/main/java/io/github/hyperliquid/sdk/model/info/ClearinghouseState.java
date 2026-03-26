package io.github.hyperliquid.sdk.model.info;

import lombok.Value;

import java.util.List;

/**
 * Perpetual clearinghouse state encapsulation (account and position overview)
 */
@Value
public class ClearinghouseState {
    /**
     * List of position information for each asset
     */
    List<AssetPositions> assetPositions;
    /**
     * Cross margin maintenance margin usage
     */
    String crossMaintenanceMarginUsed;
    /**
     * Cross margin summary
     */
    CrossMarginSummary crossMarginSummary;
    /**
     * Single currency margin summary
     */
    MarginSummary marginSummary;
    /**
     * Status timestamp (milliseconds)
     */
    Long time;
    /**
     * Withdrawable balance (string)
     */
    String withdrawable;

    @Value
    public static class CumFunding {
        /**
         * Historical cumulative funding rate impact
         */
        String allTime;
        /**
         * Cumulative since last leverage/mode change
         */
        String sinceChange;
        /**
         * Cumulative since position opening
         */
        String sinceOpen;
    }

    @Value
    public static class Leverage {
        /**
         * Original dollar scale (used for calculation)
         */
        String rawUsd;
        /**
         * Leverage type (cross/isolated)
         */
        String type;
        /**
         * Leverage multiplier value
         */
        int value;
    }

    @Value
    public static class Position {
        /**
         * Currency name
         */
        String coin;
        /**
         * Cumulative funding rate impact
         */
        CumFunding cumFunding;
        /**
         * Average opening price
         */
        String entryPx;
        /**
         * Leverage information
         */
        Leverage leverage;
        /**
         * Estimated liquidation price
         */
        String liquidationPx;
        /**
         * Margin usage
         */
        String marginUsed;
        /**
         * Maximum allowed leverage
         */
        int maxLeverage;
        /**
         * Position notional value
         */
        String positionValue;
        /**
         * Account return on equity (ROE)
         */
        String returnOnEquity;
        /**
         * Position signed quantity (positive long, negative short, string)
         */
        String szi;
        /**
         * Unrealized profit and loss
         */
        String unrealizedPnl;
    }

    @Value
    public static class AssetPositions {
        /**
         * Position details
         */
        Position position;
        /**
         * Type (e.g., perp)
         */
        String type;
    }

    @Value
    public static class CrossMarginSummary {
        /**
         * Account total value
         */
        String accountValue;
        /**
         * Total margin usage
         */
        String totalMarginUsed;
        /**
         * Total notional position
         */
        String totalNtlPos;
        /**
         * Total original dollar scale
         */
        String totalRawUsd;
    }

    @Value
    public static class MarginSummary {
        /**
         * Account total value
         */
        String accountValue;
        /**
         * Total margin usage
         */
        String totalMarginUsed;
        /**
         * Total notional position
         */
        String totalNtlPos;
        /**
         * Total original dollar scale
         */
        String totalRawUsd;
    }
}
