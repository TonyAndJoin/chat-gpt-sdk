package io.github.tonyandjoin.chatgptsdk.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author liansx
 */
@NoArgsConstructor
@Data
public class EmbeddingVO {

    @JsonProperty("object")
    private String object;
    @JsonProperty("embedding")
    private List<Double> embedding;
    @JsonProperty("index")
    private Integer index;
}
