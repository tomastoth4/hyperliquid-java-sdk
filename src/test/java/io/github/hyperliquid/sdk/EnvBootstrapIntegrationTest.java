package io.github.hyperliquid.sdk;

import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

public class EnvBootstrapIntegrationTest extends IntegrationTestBase {

    @Test
    void fromEnvBuildsWorkingTestnetClient() {
        assertNotNull(client);
        assertNotNull(client.getInfo());
        Map<String, String> mids = client.getInfo().allMids();
        assertNotNull(mids);
        assertFalse(mids.isEmpty(), "allMids() should return at least one entry on testnet");
    }
}
