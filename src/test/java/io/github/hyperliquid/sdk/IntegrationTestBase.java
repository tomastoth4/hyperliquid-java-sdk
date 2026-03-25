package io.github.hyperliquid.sdk;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assumptions;

/**
 * Base class for testnet integration tests.
 * Tests only run when HP_PK and HL_PUBLIC_KEY env vars are present.
 */
public abstract class IntegrationTestBase {

    protected HyperliquidClient client;
    protected String walletAddress;

    @BeforeEach
    void setUpClient() {
        Assumptions.assumeTrue(
            System.getenv("HP_PK") != null && !System.getenv("HP_PK").isBlank(),
            "Skipping integration test: HP_PK not set"
        );
        Assumptions.assumeTrue(
            System.getenv("HL_PUBLIC_KEY") != null && !System.getenv("HL_PUBLIC_KEY").isBlank(),
            "Skipping integration test: HL_PUBLIC_KEY not set"
        );
        client = HyperliquidClient.fromEnv(false);
        walletAddress = System.getenv("HL_PUBLIC_KEY");
    }
}
