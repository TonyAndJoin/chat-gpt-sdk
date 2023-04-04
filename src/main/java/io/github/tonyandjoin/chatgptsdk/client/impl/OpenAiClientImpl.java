package io.github.tonyandjoin.chatgptsdk.client.impl;

import com.alibaba.fastjson.JSONObject;
import io.github.tonyandjoin.chatgptsdk.auth.AuthClient;
import io.github.tonyandjoin.chatgptsdk.client.OpenAiClient;
import io.github.tonyandjoin.chatgptsdk.constant.BizConstant;
import io.github.tonyandjoin.chatgptsdk.enums.ImageSize;
import io.github.tonyandjoin.chatgptsdk.enums.Model;
import io.github.tonyandjoin.chatgptsdk.exception.SdkException;
import io.github.tonyandjoin.chatgptsdk.util.OkHttps;
import io.github.tonyandjoin.chatgptsdk.bo.*;
import io.github.tonyandjoin.chatgptsdk.vo.*;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.util.*;
import java.util.stream.Stream;

/**
 * @author liansx
 */

@Slf4j
@Service
@NoArgsConstructor
public class OpenAiClientImpl implements OpenAiClient {

    private AuthClient authClient;

    public OpenAiClientImpl(AuthClient authClient) {
        this.authClient = authClient;
    }

    public String post(Model model, String param){
        Map<String,String> header = new HashMap<>(8);
        header.put(BizConstant.Authorization,authClient.getApiKey());
        header.put(BizConstant.ContentType, BizConstant.contentValue);
        return OkHttps.post(authClient.getApiBaseUrl() + model.getUrl(), param, header,authClient.getProxy());
    }

    public String post(String url,String param){
        Map<String,String> header = new HashMap<>(8);
        header.put(BizConstant.Authorization,authClient.getApiKey());
        header.put(BizConstant.ContentType, BizConstant.contentValue);
        return OkHttps.post(authClient.getApiBaseUrl() + url, param, header,authClient.getProxy());
    }

    public String postForm(Model model, String param, ImageEditFileDTO... files){
        Map<String,String> header = new HashMap<>(8);
        header.put(BizConstant.Authorization,authClient.getApiKey());
        header.put(BizConstant.ContentType, BizConstant.contentFormValue);
        return OkHttps.postFrom(authClient.getApiBaseUrl() + model.getUrl(), param, header,authClient.getProxy(),files);
    }

    public String get(String url){
        Map<String,String> header = new HashMap<>(8);
        header.put(BizConstant.Authorization,authClient.getApiKey());
        header.put(BizConstant.ContentType, BizConstant.contentFormValue);
        return OkHttps.get(authClient.getApiBaseUrl()+url, header,authClient.getProxy());
    }

    public String delete(String url){
        Map<String,String> header = new HashMap<>(8);
        header.put(BizConstant.Authorization,authClient.getApiKey());
        header.put(BizConstant.ContentType, BizConstant.contentFormValue);
        return OkHttps.delete(authClient.getApiBaseUrl()+url, header,authClient.getProxy());
    }


    @Override
    public String fastAsk(String prompt, Integer... vars) {
        CreateFinishReq req = new CreateFinishReq();
        req.setPrompt(prompt);
        req.setModel(Model.MODEL_TEXT_DAV_003.getName());
        if(vars.length>0){
            req.setMaxTokens(vars[0]);
            if(vars.length>1){
                req.setTemperature(vars[1]);
            }
        }
        String post = post(Model.MODEL_TEXT_DAV_003, JSONObject.toJSONString(req));

        CreateFinishRes res = JSONObject.parseObject(post, CreateFinishRes.class);

        if(Objects.nonNull(res) && !CollectionUtils.isEmpty(res.getChoices())){
            StringBuilder sb = new StringBuilder();
            res.getChoices().forEach(
                    t->{
                        if(sb.toString().length()>0){
                            sb.append(",");
                        }
                        sb.append(t.getText().replaceAll("\n",""));
                    }
            );
            return sb.toString();
        }

        return post;
    }

    @Override
    public List<ChatCreateCompletionVO> chatTurbo(List<ChatCreateCompletionQuery> queryList, Model... models) {
        CreateChatFinishReq req = new CreateChatFinishReq();
        Model model;
        if(Objects.nonNull(models) && models.length>0 && Model.checkIsCompletion(models[0])){
            req.setModel(models[0].getName());
            model = models[0];
        }else{
            req.setModel(Model.MODEL_GPT_TURBO_0301.getName());
            model = Model.MODEL_GPT_TURBO_0301;
        }
        List<CreateChatFinishReq.MessagesDTO> dtoList = new ArrayList<>();
        queryList.forEach(
                t->{
                    CreateChatFinishReq.MessagesDTO dto = new CreateChatFinishReq.MessagesDTO();
                    dto.setRole(t.getRole().getName());
                    dto.setContent(t.getMessage());
                    dtoList.add(dto);
                }
        );
        req.setMessages(dtoList);
        String post = post(model, JSONObject.toJSONString(req));

        CreateChatFinishRes res = JSONObject.parseObject(post, CreateChatFinishRes.class);

        List<ChatCreateCompletionVO> resList = new ArrayList<>();

        if(Objects.nonNull(res) && !CollectionUtils.isEmpty(res.getChoices())){
            res.getChoices().forEach(
                    t->{
                        ChatCreateCompletionVO vo = new ChatCreateCompletionVO();
                        vo.setRoleName(t.getMessage().getRole());
                        vo.setContent(t.getMessage().getContent());
                        resList.add(vo);
                    }
            );
        }else if(Objects.nonNull(res)){
            SystemErrorRes systemErrorRes = JSONObject.parseObject(post, SystemErrorRes.class);
            throw new SdkException(systemErrorRes.getError().getMessage());
        }else{
            throw new SdkException("模型请求返回内容为空");
        }

        return resList;
    }

    @Override
    public String edit(String input, String instruction, Model... models) {
        EditRequest req = new EditRequest();
        Model model;
        if(Objects.nonNull(models) && models.length>0 && Model.checkIsEdit(models[0])){
            req.setModel(models[0].getName());
            model = models[0];
        }else{
            req.setModel(Model.MODEL_CODE_DAVINCI_EDIT_001.getName());
            model = Model.MODEL_CODE_DAVINCI_EDIT_001;
        }

        req.setInput(input);
        req.setInstruction(instruction);

        String post = post(model, JSONObject.toJSONString(req));

        EditResponse res = JSONObject.parseObject(post, EditResponse.class);

        if(Objects.nonNull(res) && !CollectionUtils.isEmpty(res.getChoices())){
            StringBuilder sb = new StringBuilder();
            res.getChoices().forEach(
                    t-> Stream.of(t.getText().split("\n")).forEach(sb::append)
            );
            return sb.toString();
        }
        return post;
    }

    @Override
    public List<String> image(String prompt, Integer num, ImageSize imageSize) {
        ImageReq req = new ImageReq();
        req.setN(num);
        req.setSize(imageSize.getName());
        req.setPrompt(prompt);

        String post = post(Model.IMAGE_CREATE, JSONObject.toJSONString(req));

        return handlerImageRes(post);
    }

    @Override
    public List<String> imageEdit(File image, File mask, String prompt, Integer num, ImageSize imageSize) {
        ImageReq req = ImageReq.builder()
                .prompt(prompt)
                .n(num)
                .size(imageSize.getName())
                .build();

        ImageEditFileDTO imageFile = ImageEditFileDTO.builder().fileName(BizConstant.IMAGE_NAME).file(image).build();

        String post;
        if(Objects.nonNull(mask)){
            ImageEditFileDTO maskFile = ImageEditFileDTO.builder().fileName(BizConstant.MASK_NAME).file(mask).build();
            post = postForm(Model.IMAGE_EDIT, JSONObject.toJSONString(req),imageFile,maskFile);
        }else{
            post = postForm(Model.IMAGE_EDIT, JSONObject.toJSONString(req),imageFile);
        }

        return handlerImageRes(post);
    }

    @Override
    public List<String> imageVariation(File image, Integer num, ImageSize imageSize) {
        ImageReq req = ImageReq.builder()
                .n(num)
                .size(imageSize.getName())
                .build();

        ImageEditFileDTO imageFile = ImageEditFileDTO.builder().fileName(BizConstant.IMAGE_NAME).file(image).build();

        String post = postForm(Model.IMAGE_VARIATION, JSONObject.toJSONString(req),imageFile);

        return handlerImageRes(post);
    }

    private List<String> handlerImageRes(String post) {
        List<String> urlList = new ArrayList<>();
        ImageRes res = JSONObject.parseObject(post, ImageRes.class);

        if (Objects.nonNull(res) && !CollectionUtils.isEmpty(res.getData())) {
            res.getData().forEach(t -> urlList.add(t.getUrl()));
        } else {
            throw new SdkException(post);
        }
        return urlList;
    }

    @Override
    public List<EmbeddingVO> embedding(String input) {
        EmbeddingReq req = EmbeddingReq.builder().input(input).model(Model.EMBEDDING_ADA_002.getName()).build();

        String post = post(Model.EMBEDDING_ADA_002, JSONObject.toJSONString(req));

        EmbeddingRes res = JSONObject.parseObject(post, EmbeddingRes.class);

        if(Objects.nonNull(res) && !CollectionUtils.isEmpty(res.getData())){
            return JSONObject.parseArray(JSONObject.toJSONString(res.getData()),EmbeddingVO.class);
        }else{
            throw new SdkException(post);
        }
    }

    @Override
    public String transcriptions(File file) {

        ImageEditFileDTO fileDto = ImageEditFileDTO.builder().fileName(BizConstant.FILE_NAME).file(file).build();
        TranslationReq req = TranslationReq.builder().model(Model.TRANSCRIPTIONS.getName()).build();
        String post = postForm(Model.TRANSCRIPTIONS, JSONObject.toJSONString(req), fileDto);
        TranslationRes res = JSONObject.parseObject(post, TranslationRes.class);
        if(Objects.nonNull(res)){
            return res.getText();
        }
        return post;
    }

    @Override
    public String translations(File file) {
        ImageEditFileDTO fileDto = ImageEditFileDTO.builder().fileName(BizConstant.FILE_NAME).file(file).build();
        TranslationReq req = TranslationReq.builder().model(Model.TRANSLATIONS.getName()).build();
        String post = postForm(Model.TRANSLATIONS, JSONObject.toJSONString(req), fileDto);
        TranslationRes res = JSONObject.parseObject(post, TranslationRes.class);
        if(Objects.nonNull(res)){
            return res.getText();
        }
        return post;
    }

    @Override
    public List<FileVO> fileList() {
        String post = get(Model.FILE.getUrl());
        FileListRes res = JSONObject.parseObject(post, FileListRes.class);
        if(Objects.nonNull(res) && !CollectionUtils.isEmpty(res.getData())){
            return JSONObject.parseArray(JSONObject.toJSONString(res.getData()),FileVO.class);
        }
        throw new SdkException(post);
    }

    @Override
    public FileVO file(String fileId) {
        String post = get(Model.FILE.getUrl()+ BizConstant.PRE+fileId);
        FileDetailRes res = JSONObject.parseObject(post, FileDetailRes.class);
        if(Objects.nonNull(res)){
            return JSONObject.parseObject(JSONObject.toJSONString(res),FileVO.class);
        }
        throw new SdkException(post);
    }

    @Override
    public FileVO fileUpload(File file) {
        FileUploadReq req = FileUploadReq.builder().purpose(BizConstant.PURPOSE).build();
        ImageEditFileDTO fileDto = ImageEditFileDTO.builder().file(file).fileName(BizConstant.FILE_NAME).build();
        String post = postForm(Model.FILE,JSONObject.toJSONString(req),fileDto);
        FileDetailRes res = JSONObject.parseObject(post, FileDetailRes.class);
        if(Objects.nonNull(res)){
            return JSONObject.parseObject(JSONObject.toJSONString(res),FileVO.class);
        }
        throw new SdkException(post);
    }

    @Override
    public Boolean fileDelete(String fileId) {
        String post =  delete(Model.FILE.getUrl()+ BizConstant.PRE+fileId);

        FileDeleteRes res = JSONObject.parseObject(post,FileDeleteRes.class);

        if(Objects.nonNull(res) && res.getDeleted()){
            return true;
        }
        throw new SdkException(post);
    }

    @Override
    public String showFile(String fileId) {
        return get(String.format(Model.FILE_SHOW.getUrl(),fileId));
    }

    @Override
    public FineTunesCreateVO createFineTunes(String fileId) {
        FineTunesCreateReq req = FineTunesCreateReq.builder()
                .trainingFile(fileId)
                .build();
        String post = post(Model.FINE_TUNES, JSONObject.toJSONString(req));
        return JSONObject.parseObject(post,FineTunesCreateVO.class);
    }

    @Override
    public List<FineTunesDetailVO> fineTunesList() {
        String post = get(Model.FINE_TUNES.getUrl());
        FineTunesListRes res = JSONObject.parseObject(post, FineTunesListRes.class);
        if(Objects.nonNull(res) && !CollectionUtils.isEmpty(res.getData())){
            return JSONObject.parseArray(JSONObject.toJSONString(res.getData()),FineTunesDetailVO.class);
        }
        throw new SdkException(post);
    }

    @Override
    public FineTunesInfoVO fineTunes(String fineTunesId) {
        String post = get(Model.FINE_TUNES.getUrl()+BizConstant.PRE+fineTunesId);
        return JSONObject.parseObject(post, FineTunesInfoVO.class);

    }

    @Override
    public FineTunesCancelVO fineTunesCancel(String fineTunesId) {
        String post = post(String.format(Model.FINE_TUNES_CANCEL.getUrl(),fineTunesId), "");
        return JSONObject.parseObject(post,FineTunesCancelVO.class);
    }

    @Override
    public List<FineTunesEventVO> fineTunesEvent(String fineTunesId) {
        String post = get(String.format(Model.FINE_TUNES_EVENT.getUrl(), fineTunesId));
        FineTunesEventRes res = JSONObject.parseObject(post, FineTunesEventRes.class);
        if(Objects.nonNull(res) && !CollectionUtils.isEmpty(res.getData())){
            return JSONObject.parseArray(JSONObject.toJSONString(res.getData()),FineTunesEventVO.class);
        }
        throw new SdkException(post);
    }

    @Override
    public Boolean fineTunesDelete(String fineTunesId) {
        String delete = delete(Model.MODELS.getUrl() + BizConstant.PRE + fineTunesId);
        FineTunesDeleteRes res = JSONObject.parseObject(delete, FineTunesDeleteRes.class);
        if(Objects.nonNull(res) && Objects.nonNull(res.getDeleted()) && res.getDeleted()){
            return true;
        }
        throw new SdkException(delete);
    }

    @Override
    public List<ModerationVO> moderation(String input) {
        ModerationReq build = ModerationReq.builder().input(input).build();
        String post = post(Model.MODE_RATIONS, JSONObject.toJSONString(build));
        ModerationRes res = JSONObject.parseObject(post, ModerationRes.class);
        if(Objects.nonNull(res) && !CollectionUtils.isEmpty(res.getResults())){
            return JSONObject.parseArray(JSONObject.toJSONString(res.getResults()),ModerationVO.class);
        }
        throw new SdkException(post);
    }
}
