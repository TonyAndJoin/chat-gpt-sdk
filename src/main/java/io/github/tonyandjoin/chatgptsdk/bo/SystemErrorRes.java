package io.github.tonyandjoin.chatgptsdk.bo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liansx
 */
@NoArgsConstructor
@Data
public class SystemErrorRes {

    @JsonProperty("error")
    private ErrorDTO error;

    @NoArgsConstructor
    @Data
    public static class ErrorDTO {
        @JsonProperty("message")
        private String message;
        @JsonProperty("type")
        private String type;
        @JsonProperty("param")
        private Object param;
        @JsonProperty("code")
        private Object code;
    }
}
