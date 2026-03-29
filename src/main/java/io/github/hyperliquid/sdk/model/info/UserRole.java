package io.github.hyperliquid.sdk.model.info;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRole {
    String role;
    UserRoleData data;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class UserRoleData {
        String user;
        String master;
    }
}
