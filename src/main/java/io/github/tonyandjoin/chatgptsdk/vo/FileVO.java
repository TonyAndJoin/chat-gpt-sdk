package io.github.tonyandjoin.chatgptsdk.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liansx
 */
@NoArgsConstructor
@Data
public class FileVO {

    @JsonProperty("id")
    private String id;
    @JsonProperty("object")
    private String object;
    @JsonProperty("bytes")
    private Integer bytes;
    @JsonProperty("created_at")
    private Integer createdAt;
    @JsonProperty("filename")
    private String filename;
    @JsonProperty("purpose")
    private String purpose;
}
