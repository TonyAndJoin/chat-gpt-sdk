package io.github.tonyandjoin.chatgptsdk.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liansx
 */
@NoArgsConstructor
@Data
public class ModerationVO {


    @JsonProperty("categories")
    private CategoriesDTO categories;
    @JsonProperty("category_scores")
    private CategoryScoresDTO categoryScores;
    @JsonProperty("flagged")
    private Boolean flagged;

    @NoArgsConstructor
    @Data
    public static class CategoriesDTO {
        @JsonProperty("hate")
        private Boolean hate;
        @JsonProperty("threatening")
        private Boolean threatening;
        @JsonProperty("self-harm")
        private Boolean selfharm;
        @JsonProperty("sexual")
        private Boolean sexual;
        @JsonProperty("minors")
        private Boolean minors;
        @JsonProperty("violence")
        private Boolean violence;
        @JsonProperty("graphic")
        private Boolean graphic;
    }

    @NoArgsConstructor
    @Data
    public static class CategoryScoresDTO {
        @JsonProperty("hate")
        private Double hate;
        @JsonProperty("threatening")
        private Double threatening;
        @JsonProperty("self-harm")
        private Double selfharm;
        @JsonProperty("sexual")
        private Double sexual;
        @JsonProperty("minors")
        private Double minors;
        @JsonProperty("violence")
        private Double violence;
        @JsonProperty("graphic")
        private Double graphic;
    }
}
