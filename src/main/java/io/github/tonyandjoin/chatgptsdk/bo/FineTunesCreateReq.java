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
public class FineTunesCreateReq {

    @JsonProperty("training_file")
    private String trainingFile;

    @JsonProperty(value = "validation_file")
    private String validationFile;

    @JsonProperty(value = "model")
    private String model;

}
