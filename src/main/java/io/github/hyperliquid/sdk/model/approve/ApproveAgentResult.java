package io.github.hyperliquid.sdk.model.approve;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Value;

/**
 * ApproveAgentResult wraps the return value of approveAgent:
 * - response: server /exchange JSON response;
 * - agentPrivateKey: newly generated Agent private key (0x prefix hexadecimal string);
 * - agentAddress: newly generated Agent address (0x prefix hexadecimal string).
 */
@Value
public class ApproveAgentResult {

    /** Server response JSON
     */
    JsonNode response;

    /** Newly generated Agent private key (0x prefix)
     */
    String agentPrivateKey;

    /** Newly generated Agent address (0x prefix)
     */
    String agentAddress;
}
