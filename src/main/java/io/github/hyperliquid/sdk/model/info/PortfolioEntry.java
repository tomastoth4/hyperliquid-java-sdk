package io.github.hyperliquid.sdk.model.info;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/** Portfolio entry as array-of-tuples (in the form of [period, data]) */
@JsonFormat(shape = JsonFormat.Shape.ARRAY)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PortfolioEntry {
    /** Index 0: time period label */
    @JsonProperty(index = 0)
    private String period;

    /** Index 1: portfolio data for the period */
    @JsonProperty(index = 1)
    private PortfolioData data;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class PortfolioData {
        private List<List<Object>> accountValueHistory;
        private List<List<Object>> pnlHistory;
        private String vlm;
    }
}
