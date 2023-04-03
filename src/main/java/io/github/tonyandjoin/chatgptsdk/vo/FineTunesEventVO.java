package io.github.tonyandjoin.chatgptsdk.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liansx
 */
@NoArgsConstructor
@Data
public class FineTunesEventVO {

    @JsonProperty("object")
    private String object;
    @JsonProperty("created_at")
    private Integer createdAt;
    @JsonProperty("level")
    private String level;
    @JsonProperty("message")
    private String message;
}
