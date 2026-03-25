package io.github.hyperliquid.sdk.model.websocket;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import io.github.hyperliquid.sdk.model.info.UserFill;
import io.github.hyperliquid.sdk.utils.JSONUtil;

import java.io.IOException;
import java.util.List;

public class UserEventsMessageDeserializer extends StdDeserializer<UserEventsMessage> {

    public UserEventsMessageDeserializer() {
        super(UserEventsMessage.class);
    }

    @Override
    public UserEventsMessage deserialize(JsonParser p, DeserializationContext ctx) throws IOException {
        JsonNode node = p.getCodec().readTree(p);
        UserEventsMessage msg = new UserEventsMessage();
        if (node.has("fills")) {
            msg.setFills(JSONUtil.toList(node.get("fills"), UserFill.class));
        } else if (node.has("funding")) {
            msg.setFunding(JSONUtil.treeToValue(node.get("funding"), WsFunding.class));
        } else if (node.has("liquidation")) {
            msg.setLiquidation(JSONUtil.treeToValue(node.get("liquidation"), WsLiquidation.class));
        } else if (node.has("nonUserCancel")) {
            msg.setNonUserCancels(JSONUtil.toList(node.get("nonUserCancel"), WsNonUserCancel.class));
        }
        return msg;
    }
}
