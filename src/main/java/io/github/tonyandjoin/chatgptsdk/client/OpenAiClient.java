package io.github.tonyandjoin.chatgptsdk.client;

import io.github.tonyandjoin.chatgptsdk.enums.ImageSize;
import io.github.tonyandjoin.chatgptsdk.enums.Model;
import io.github.tonyandjoin.chatgptsdk.vo.*;

import java.io.File;
import java.util.List;

/**
 * @author liansx
 */
public interface OpenAiClient {

    /**
     * 快速提问
     * @param prompt 问题内容
     * @param vars 可选填最大令牌数 maxTokens、temperature
     * @return 返回提问内容
     */
    String fastAsk(String prompt,Integer ... vars);

    /**
     * turbo模型的聊天
     * @param queryList 请求内容，角色和message组成的数组
     * @param models 模型，可不传，不传默认使用 gpt-3.5-turbo-0301
     * @return 返回应答内容
     */
    List<ChatCreateCompletionVO> chatTurbo(List<ChatCreateCompletionQuery> queryList, Model... models);

    /**
     * edit模型处理数据
     * @param input 输入内容
     * @param instruction 描述
     * @param models 模型 可不传，不传默认使用 code-davinci-edit-001
     * @return 返回模型内容
     */
    String edit(String input,String instruction,Model ... models);


    /**
     * 图片生成模型
     * @param prompt 图片描述
     * @param num 数量
     * @param imageSize 图片大小
     * @return 返回图片链接list
     */
    List<String> image(String prompt, Integer num, ImageSize imageSize);

    /**
     * 图片编辑
     * @param image 要编辑的图像。必须是有效的 PNG 文件，小于 4MB，并且是方形的。如果未提供遮罩，图像必须具有透明度，将用作遮罩。
     * @param mask 附加图像，其完全透明区域（例如，alpha 为零的区域）指示image应编辑的位置。必须是有效的 PNG 文件，小于 4MB
     * @param prompt 所需图像的文本描述。最大长度为 1000 个字符。
     * @param num 数量
     * @param imageSize 尺寸
     * @return 返回图片List
     */
    List<String> imageEdit(File image, File mask, String prompt, Integer num, ImageSize imageSize);

    /**
     * 生成图像变体
     * @param image 用作变体基础的图像。必须是有效的 PNG 文件，小于 4MB，并且是方形的。
     * @param num 要生成的图像数。必须介于 1 和 10 之间。
     * @param imageSize 生成图像的大小。必须是256x256、512x512或 之一1024x1024。
     * @return 返回图片List
     */
    List<String> imageVariation(File image,Integer num,ImageSize imageSize);

    /**
     * 嵌入模型
     * @param input 输入内容
     * @return 返回嵌入模型内容
     */
    List<EmbeddingVO> embedding(String input);

    /**
     * 音视频转录
     * @param file 音视频文件
     * @return 返回内容
     */
    String transcriptions(File file);

    /**
     * 音视频转录翻译
     * @param file 音视频文件
     * @return 返回内容
     */
    String translations(File file);

    /**
     * 检索文件
     * @return 返回文件list
     */
    List<FileVO> fileList();

    /**
     * 检索文件
     * @param fileId 文件id
     * @return 返回文件内容
     */
    FileVO file(String fileId);

    /**
     * 上传文件
     * @param file 文件
     * @return 返回文件实体内容
     */
    FileVO fileUpload(File file);

    /**
     * 删除文件
     * @param fileId 文件id
     * @return true表示删除成功
     */
    Boolean fileDelete(String fileId);

    /**
     * 免费版本无法使用该接口
     * @param fileId 文件id
     * @return 返回文件内容
     */
    String showFile(String fileId);

    /**
     * 创建微调
     * @param fileId 文件id
     * @return 返回微调模型
     */
    FineTunesCreateVO createFineTunes(String fileId);

    /**
     * 微调列表
     * @return 返回微调列表信息
     */
    List<FineTunesDetailVO> fineTunesList();

    /**
     * 微调详情
     * @param fineTunesId 微调id
     * @return 获取微调信息
     */
    FineTunesInfoVO fineTunes(String fineTunesId);

    /**
     * 取消微调
     * @param fineTunesId 微调id
     * @return 返回微调信息
     */
    FineTunesCancelVO fineTunesCancel(String fineTunesId);

    /**
     * 微调事件
     * @param fineTunesId 微调id
     * @return 返回微调事件信息
     */
    List<FineTunesEventVO> fineTunesEvent(String fineTunesId);

    /**
     * 删除微调信息
     * @param fineTunesId 微调id
     * @return true
     */
    Boolean fineTunesDelete(String fineTunesId);

    /**
     *  分类文本是否违反 OpenAI 的内容政策
     * @param input 输入内容
     * @return 返回参数
     */
    List<ModerationVO> moderation(String input);
}
