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
public class CreateFinishRes {

    @JsonProperty("id")
    private String id;
    @JsonProperty("object")
    private String object;
    @JsonProperty("created")
    private Integer created;
    @JsonProperty("model")
    private String model;
    @JsonProperty("choices")
    private List<ChoicesDTO> choices;
    @JsonProperty("usage")
    private UsageDTO usage;

    @NoArgsConstructor
    @Data
    public static class UsageDTO {
        @JsonProperty("prompt_tokens")
        private Integer promptTokens;
        @JsonProperty("completion_tokens")
        private Integer completionTokens;
        @JsonProperty("total_tokens")
        private Integer totalTokens;
    }

    @NoArgsConstructor
    @Data
    public static class ChoicesDTO {
        @JsonProperty("text")
        private String text;
        @JsonProperty("index")
        private Integer index;
        @JsonProperty("logprobs")
        private Object logprobs;
        @JsonProperty("finish_reason")
        private String finishReason;
    }
}
