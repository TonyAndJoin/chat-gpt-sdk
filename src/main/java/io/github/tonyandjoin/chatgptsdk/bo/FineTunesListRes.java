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
public class FineTunesListRes {

    @JsonProperty("object")
    private String object;
    @JsonProperty("data")
    private List<FineTunesDetailRes> data;
}
