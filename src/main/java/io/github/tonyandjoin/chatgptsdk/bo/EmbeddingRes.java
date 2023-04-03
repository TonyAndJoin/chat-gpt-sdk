package io.github.tonyandjoin.chatgptsdk.bo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author liansx
 */
@NoArgsConstructor
@Data
public class EmbeddingRes {

    @JsonProperty("object")
    private String object;
    @JsonProperty("data")
    private List<DataDTO> data;
    @JsonProperty("model")
    private String model;
    @JsonProperty("usage")
    private UsageDTO usage;

    @NoArgsConstructor
    @Data
    public static class UsageDTO {
        @JsonProperty("prompt_tokens")
        private Integer promptTokens;
        @JsonProperty("total_tokens")
        private Integer totalTokens;
    }

    @NoArgsConstructor
    @Data
    public static class DataDTO {
        @JsonProperty("object")
        private String object;
        @JsonProperty("embedding")
        private List<Double> embedding;
        @JsonProperty("index")
        private Integer index;
    }
}
