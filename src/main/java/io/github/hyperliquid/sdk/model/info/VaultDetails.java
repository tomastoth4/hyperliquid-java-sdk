package io.github.hyperliquid.sdk.model.info;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/** Vault details including followers and configuration */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class VaultDetails {
    private String name;
    private String vaultAddress;
    private String leader;
    private String description;
    private Object portfolio;
    private Double apr;
    private Double leaderFraction;
    private Double leaderCommission;
    private List<VaultFollower> followers;
    private VaultFollower followerState;
    private Double maxDistributable;
    private Double maxWithdrawable;
    private Boolean isClosed;
    private Boolean allowDeposits;
    private Boolean alwaysCloseOnWithdraw;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class VaultFollower {
        private String user;
        private String vaultEquity;
        private String pnl;
        private String allTimePnl;
        private Integer daysFollowing;
        private Long vaultEntryTime;
        private Long lockupUntil;
    }
}
