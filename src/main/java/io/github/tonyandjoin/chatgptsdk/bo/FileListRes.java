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
public class FileListRes {

    @JsonProperty("data")
    private List<FileDetailRes> data;
    @JsonProperty("object")
    private String object;

}
