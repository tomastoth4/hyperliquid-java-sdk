package io.github.hyperliquid.sdk;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;

/**
 * Base class for testnet integration tests.
 * Tests only run when HP_PK and HL_PUBLIC_KEY env vars are present.
 */
@EnabledIfEnvironmentVariable(named = "HP_PK", matches = ".+")
@EnabledIfEnvironmentVariable(named = "HL_PUBLIC_KEY", matches = ".+")
public abstract class IntegrationTestBase {

    protected HyperliquidClient client;
    protected String walletAddress;

    @BeforeEach
    void setUpClient() {
        client = HyperliquidClient.fromEnv(false);
        walletAddress = System.getenv("HL_PUBLIC_KEY");
    }
}
