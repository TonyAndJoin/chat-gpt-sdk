package io.github.tonyandjoin.chatgptsdk.bo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liansx
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageEditReq {

    @JsonProperty("image")
    private String image;
    @JsonProperty("mask")
    private String mask;
    @JsonProperty("prompt")
    private String prompt;
    @JsonProperty("n")
    private Integer n;
    @JsonProperty("size")
    private String size;
}
