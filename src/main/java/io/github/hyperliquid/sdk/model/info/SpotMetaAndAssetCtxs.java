package io.github.hyperliquid.sdk.model.info;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/** Spot metadata and asset contexts array (in the form of [spotMeta, assetCtxs]) */
@JsonFormat(shape = JsonFormat.Shape.ARRAY)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpotMetaAndAssetCtxs {
    /** Index 0: spot market metadata */
    @JsonProperty(index = 0)
    private SpotMeta spotMeta;

    /** Index 1: list of spot asset contexts */
    @JsonProperty(index = 1)
    private List<SpotAssetCtx> assetCtxs;
}
