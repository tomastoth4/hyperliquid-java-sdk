package io.github.hyperliquid.sdk;

import io.github.hyperliquid.sdk.utils.HypeError;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HyperliquidClientFromEnvTest {

    @Test
    void fromEnvThrowsWhenHpPkMissing() {
        org.junit.jupiter.api.Assumptions.assumeTrue(
            System.getenv("HP_PK") == null || System.getenv("HP_PK").isBlank(),
            "Skip: HP_PK is set in environment"
        );
        assertThrows(HypeError.class, () -> HyperliquidClient.fromEnv());
    }

    @Test
    void fromEnvTestnetOverload() {
        if (System.getenv("HP_PK") != null && System.getenv("HL_PUBLIC_KEY") != null) {
            assertDoesNotThrow(() -> {
                HyperliquidClient client = HyperliquidClient.fromEnv(false);
                assertNotNull(client);
                assertNotNull(client.getInfo());
            });
        }
    }
}
