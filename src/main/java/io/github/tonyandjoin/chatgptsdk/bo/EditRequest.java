package io.github.tonyandjoin.chatgptsdk.bo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liansx
 */
@NoArgsConstructor
@Data
public class EditRequest {

    @JsonProperty("model")
    private String model;
    @JsonProperty("input")
    private String input;
    @JsonProperty("instruction")
    private String instruction;
}
