package io.github.tonyandjoin.chatgptsdk.bo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liansx
 */
@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class EmbeddingReq {

    @JsonProperty("model")
    private String model;
    @JsonProperty("input")
    private String input;
}
