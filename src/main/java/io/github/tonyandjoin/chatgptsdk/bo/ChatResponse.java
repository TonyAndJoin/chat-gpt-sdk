package io.github.tonyandjoin.chatgptsdk.bo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class ChatResponse {


    private String id;
    private String object;
    private Integer created;
    private String model;
    private List<ChoicesDTO> choices;
    private UsageDTO usage;

    @NoArgsConstructor
    @Data
    public static class UsageDTO {
        private Integer promptTokens;
        private Integer completionTokens;
        private Integer totalTokens;
    }

    @NoArgsConstructor
    @Data
    public static class ChoicesDTO {
        private String text;
        private Integer index;
        private Object logprobs;
        private String finishReason;
    }
}
