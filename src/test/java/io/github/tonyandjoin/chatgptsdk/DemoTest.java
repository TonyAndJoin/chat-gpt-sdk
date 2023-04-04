package io.github.tonyandjoin.chatgptsdk;


import com.alibaba.fastjson.JSONObject;
import io.github.tonyandjoin.chatgptsdk.auth.AuthClient;
import io.github.tonyandjoin.chatgptsdk.enums.ImageSize;
import io.github.tonyandjoin.chatgptsdk.enums.Model;
import io.github.tonyandjoin.chatgptsdk.enums.Role;
import io.github.tonyandjoin.chatgptsdk.util.SpringUtil;
import io.github.tonyandjoin.chatgptsdk.vo.*;
import org.junit.platform.commons.util.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DemoTest {

    private static final String API_ENDPOINT = "https://api.openai.com/v1";
    private static final String API_KEY = "";
    private static final String proxyIp = "";
    private static final Integer proxyPort = 0;

    public static void main(String[] args) {

//        testAuthClient();

        testFastAsk("spring是什么");

//        testCreateChat();

//        testEdit("我爱的人就是我的爱人","这是哪首歌的歌词");

//        testImage("特朗普证件照",1, ImageSize.LIT);

//        testEditImage("src/main/resources/image/image.png",
//                "src/main/resources/image/image.png","A big black dog",1,ImageSize.LIT);

//        testVariationImage("src/main/resources/image/image.png",1,ImageSize.LIT);

//        embedding("特朗普证件照");

//        translations("src/main/resources/image/music.mp3");

//        fileHandler();

//        fineTunes();
    }

    private static void fineTunes() {

        FineTunesCreateVO fineTunes = getAuthClient().getClient().createFineTunes("file-odLNHT7zogWizdl31m67CRxO");
        System.out.println(JSONObject.toJSONString(fineTunes));

//        List<FineTunesDetailVO> voList = getAuthClient().getClient().fineTunesList();
//        System.out.println("列表"+JSONObject.toJSONString(voList));
//
//        FineTunesInfoVO fineTunesInfoVO = getAuthClient().getClient().fineTunes(fineTunes.getId());
//        System.out.println("详情"+JSONObject.toJSONString(fineTunesInfoVO));
//
//        List<FineTunesEventVO> fineTunesEventVOS = getAuthClient().getClient().fineTunesEvent(fineTunes.getId());
//        System.out.println("事件"+JSONObject.toJSONString(fineTunesEventVOS));
//
//        FineTunesCancelVO fineTunesCancelVO = getAuthClient().getClient().fineTunesCancel(fineTunes.getId());
//        System.out.println("取消"+JSONObject.toJSONString(fineTunesCancelVO));

//        Boolean aBoolean = getAuthClient().getClient().fineTunesDelete(fineTunes.getId());
//        System.out.println(aBoolean);

    }

    private static void fileHandler() {
//        File file = new File("src/main/resources/image/handler.JSONL");
//
//        FileVO fileVO = getAuthClient().getClient().fileUpload(file);
//        System.out.println("上传"+JSONObject.toJSONString(fileVO));

        List<FileVO> fileVOS = getAuthClient().getClient().fileList();
        System.out.println("列表"+JSONObject.toJSONString(fileVOS));

//        FileVO fileVO1 = getAuthClient().getClient().file(fileVO.getId());
//        System.out.println("检索"+JSONObject.toJSONString(fileVO1));
//
//        Boolean aBoolean = getAuthClient().getClient().fileDelete(fileVO.getId());
//
//        System.out.println(aBoolean);
    }

    private static void translations(String var) {
        File file = new File(var);

        String transcriptions = getAuthClient().getClient().transcriptions(file);
        System.out.println(transcriptions);

        String translations = getAuthClient().getClient().translations(file);
        System.out.println(translations);

    }

    private static void embedding(String var) {
        List<EmbeddingVO> embedding = getAuthClient().getClient().embedding(var);
        System.out.println(JSONObject.toJSONString(embedding));
    }

    private static void testVariationImage(String var, int i, ImageSize lit) {
        File file = new File(var);
        List<String> image = getAuthClient().getClient().imageVariation(file,i, lit);
        System.out.println(JSONObject.toJSONString(image));
    }

    private static void testEditImage(String var, String var1, String var2, int i, ImageSize imageSize) {
        File file = new File(var);
        File file1 = null;
        if(!StringUtils.isBlank(var1)){
            file1 = new File(var1);
        }

        List<String> image = getAuthClient().getClient().imageEdit(file, file1,var2,i, imageSize);
        System.out.println(JSONObject.toJSONString(image));
    }

    private static void testImage(String prompt, Integer num, ImageSize imageSize) {
        List<String> image = getAuthClient().getClient().image(prompt, num, imageSize);
        System.out.println(JSONObject.toJSONString(image));
    }

    private static void testEdit(String input, String instruction) {
        String edit = getAuthClient().getClient().edit(input, instruction);
        System.out.println(edit);
    }

    private static AuthClient getAuthClient() {
        AuthClient authClient = new AuthClient(API_KEY,API_ENDPOINT,proxyIp,proxyPort);
        return authClient;
    }

    private static void testCreateChat() {
        AuthClient authClient = getAuthClient();
        List<ChatCreateCompletionQuery> queryList = new ArrayList<>();
        ChatCreateCompletionQuery query = new ChatCreateCompletionQuery();
        query.setRole(Role.USER);
        query.setMessage("我是小丽，比小红大4岁");
        ChatCreateCompletionQuery query2 = new ChatCreateCompletionQuery();
        query2.setRole(Role.USER);
        query2.setMessage("我是小亮，比小丽大2岁");
        ChatCreateCompletionQuery query3 = new ChatCreateCompletionQuery();
        query3.setRole(Role.SYSTEM);
        query3.setMessage("小丽和小亮的年龄和是小红的两倍");
        ChatCreateCompletionQuery query4 = new ChatCreateCompletionQuery();
        query4.setRole(Role.ASSISTANT);
        query4.setMessage("小丽、小亮、小红的年龄分别是多少");
        queryList.add(query);
        queryList.add(query2);
        queryList.add(query3);
        queryList.add(query4);
        List<ChatCreateCompletionVO> list = authClient.getClient().chatTurbo(queryList, Model.MODEL_GPT_TURBO);
        System.out.println(JSONObject.toJSONString(list));
    }


    private static void testFastAsk(String content) {
        AuthClient authClient = getAuthClient();
        String s = authClient.getClient().fastAsk(content,100,0);
        System.out.println(s);
    }

    public static void testAuthClient(){
        AuthClient bean = SpringUtil.getBean(AuthClient.class);
        System.out.println(bean);
        System.out.println(bean.getClient());
    }
}
