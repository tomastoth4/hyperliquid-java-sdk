package io.github.hyperliquid.sdk.model.websocket;

import lombok.Value;
import java.util.List;

@Value
public class TradeMessage {
    List<WsTrade> trades;
}
