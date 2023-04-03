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
public class EditResponse {

    @JsonProperty("object")
    private String object;
    @JsonProperty("created")
    private Integer created;
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
    }
}
