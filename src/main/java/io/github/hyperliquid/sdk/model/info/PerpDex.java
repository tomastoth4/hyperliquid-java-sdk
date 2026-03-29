package io.github.hyperliquid.sdk.model.info;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * PerpDex typed model.
 *
 * <p>
 * This model preserves extension fields to be compatible with future return structures.
 * </p>
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PerpDex {

    /**
     * Dex name
     */
    private String name;

    /**
     * Other unknown/extension fields
     */
    private Map<String, Object> extensions = new LinkedHashMap<>();

    /**
     * Get extension fields
     */
    @JsonAnyGetter
    public Map<String, Object> any() {
        return extensions;
    }

    /**
     * Set extension fields
     */
    @JsonAnySetter
    public void set(String key, Object value) {
        if (this.extensions == null) {
            this.extensions = new LinkedHashMap<>();
        }
        this.extensions.put(key, value);
    }
}
