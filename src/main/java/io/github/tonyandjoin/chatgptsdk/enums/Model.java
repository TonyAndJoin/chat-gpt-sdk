package io.github.tonyandjoin.chatgptsdk.enums;

import io.github.tonyandjoin.chatgptsdk.exception.SdkException;
import lombok.Getter;

/**
 * @author liansx
 * 请求模型
 */

@Getter
public enum Model {

    MODEL_TEXT_DAV_003("text-davinci-003","/completions","聊天消息模型"),

    MODEL_GPT_TURBO("gpt-3.5-turbo","/chat/completions","创建聊天消息模型"),

    MODEL_GPT_TURBO_0301("gpt-3.5-turbo-0301","/chat/completions","创建聊天消息模型"),

    MODEL_DAVINCI_EDIT_001("text-davinci-edit-001","/edits","修改输入内容模型"),

    MODEL_CODE_DAVINCI_EDIT_001("code-davinci-edit-001","/edits","修改输入内容模型"),

    IMAGE_CREATE("","/images/generations","生成图片"),

    IMAGE_EDIT("","/images/edits","编辑图片"),

    IMAGE_VARIATION("","/images/variations","图片变体"),

    EMBEDDING_ADA_002("text-embedding-ada-002","/embeddings","嵌入"),

    TRANSCRIPTIONS("whisper-1","/audio/transcriptions","音视频转录"),

    TRANSLATIONS("whisper-1","/audio/translations","音视频转录翻译"),

    FILE("","/files","文件"),

    FILE_SHOW("","/files/%s/content","文件详情"),

    FINE_TUNES("","/fine-tunes","微调模型"),

    FINE_TUNES_CANCEL("","/fine-tunes/%s/cancel","取消微调模型"),

    FINE_TUNES_EVENT("","/fine-tunes/%s/events","微调事件"),

    MODE_RATIONS("","/moderations","创建适度"),

    MODELS("","/models","模型"),

    ;

    private String name;

    private String url;

    private String desc;

    Model(String name, String url, String desc) {
        this.name = name;
        this.url = url;
        this.desc = desc;
    }

    public static boolean checkIsCompletion(Model model) {
        switch (model){
            case MODEL_GPT_TURBO :
            case MODEL_GPT_TURBO_0301 : return true;
            default:throw new SdkException("聊天模型只能选用MODEL_GPT_TURBO 或者 MODEL_GPT_TURBO_0301");
        }
    }

    public static boolean checkIsEdit(Model model) {
        switch (model){
            case MODEL_CODE_DAVINCI_EDIT_001:
            case MODEL_DAVINCI_EDIT_001:  return true;
            default:throw new SdkException("聊天模型只能选用 MODEL_CODE_DAVINCI_EDIT_001 或者 MODEL_DAVINCI_EDIT_001");
        }
    }
}

