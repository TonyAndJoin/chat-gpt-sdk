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
public class CreateChatFinishReq {

    @JsonProperty("model")
    private String model;
    @JsonProperty("messages")
    private List<MessagesDTO> messages;

    @NoArgsConstructor
    @Data
    public static class MessagesDTO {
        @JsonProperty("role")
        private String role;
        @JsonProperty("content")
        private String content;
    }
}
