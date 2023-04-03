package io.github.tonyandjoin.chatgptsdk.bo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ChatRequest {


    @JSONField(name = "model")
    private String model;
    @JSONField(name = "prompt")
    private String prompt;
    @JSONField(name = "temperature")
    private Integer temperature;
    @JSONField(name = "max_tokens")
    private Integer maxTokens;
}