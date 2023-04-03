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
public class FineTunesEventRes {

    @JsonProperty("object")
    private String object;
    @JsonProperty("data")
    private List<DataDTO> data;

    @NoArgsConstructor
    @Data
    public static class DataDTO {
        @JsonProperty("object")
        private String object;
        @JsonProperty("created_at")
        private Integer createdAt;
        @JsonProperty("level")
        private String level;
        @JsonProperty("message")
        private String message;
    }
}
