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
public class ImageReq {

    @JsonProperty("prompt")
    private String prompt;
    @JsonProperty("n")
    private Integer n;
    @JsonProperty("size")
    private String size;
}
