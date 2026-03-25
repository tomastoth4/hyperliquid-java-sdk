package io.github.hyperliquid.sdk.model.websocket;

import io.github.hyperliquid.sdk.model.info.AssetCtx;

public class ActiveAssetCtxMessage {
    private String coin;
    private AssetCtx ctx;

    public String getCoin() { return coin; }
    public void setCoin(String coin) { this.coin = coin; }
    public AssetCtx getCtx() { return ctx; }
    public void setCtx(AssetCtx ctx) { this.ctx = ctx; }
}
