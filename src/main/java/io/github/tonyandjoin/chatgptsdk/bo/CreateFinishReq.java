package io.github.tonyandjoin.chatgptsdk.bo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liansx
 */
@NoArgsConstructor
@Data
public class CreateFinishReq {

    @JSONField(name = "model")
    private String model;
    @JSONField(name = "prompt")
    private String prompt;
    @JSONField(name = "max_tokens")
    private Integer maxTokens;
    @JSONField(name = "temperature")
    private Integer temperature;
}
