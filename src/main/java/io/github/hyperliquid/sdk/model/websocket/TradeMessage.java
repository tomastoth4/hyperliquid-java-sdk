package io.github.hyperliquid.sdk.model.websocket;

import java.util.List;

public class TradeMessage {
    private List<WsTrade> trades;

    public List<WsTrade> getTrades() { return trades; }
    public void setTrades(List<WsTrade> trades) { this.trades = trades; }
}
