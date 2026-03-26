package io.github.hyperliquid.sdk.model.websocket;

import io.github.hyperliquid.sdk.model.info.AssetCtx;
import lombok.Value;

@Value
public class ActiveAssetCtxMessage {
    String coin;
    AssetCtx ctx;
}
