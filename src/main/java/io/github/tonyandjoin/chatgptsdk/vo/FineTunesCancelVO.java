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
public class FineTunesCancelVO {

    @JsonProperty("id")
    private String id;
    @JsonProperty("object")
    private String object;
    @JsonProperty("model")
    private String model;
    @JsonProperty("created_at")
    private Integer createdAt;
    @JsonProperty("events")
    private List<EventsDTO> events;
    @JsonProperty("fine_tuned_model")
    private Object fineTunedModel;
    @JsonProperty("hyperparams")
    private HyperparamsDTO hyperparams;
    @JsonProperty("organization_id")
    private String organizationId;
    @JsonProperty("result_files")
    private List<?> resultFiles;
    @JsonProperty("status")
    private String status;
    @JsonProperty("validation_files")
    private List<?> validationFiles;
    @JsonProperty("training_files")
    private List<TrainingFilesDTO> trainingFiles;
    @JsonProperty("updated_at")
    private Integer updatedAt;

    @NoArgsConstructor
    @Data
    public static class HyperparamsDTO {
    }

    @NoArgsConstructor
    @Data
    public static class EventsDTO {
    }

    @NoArgsConstructor
    @Data
    public static class TrainingFilesDTO {
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
}
