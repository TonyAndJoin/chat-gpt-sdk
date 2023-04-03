package io.github.tonyandjoin.chatgptsdk;

import cn.hutool.http.Header;
import com.alibaba.fastjson.JSONObject;
import io.github.tonyandjoin.chatgptsdk.bo.ChatRequest;
import io.github.tonyandjoin.chatgptsdk.bo.ChatResponse;
import io.github.tonyandjoin.chatgptsdk.bo.CreateChatFinishReq;
import io.github.tonyandjoin.chatgptsdk.enums.Model;
import io.github.tonyandjoin.chatgptsdk.enums.Role;
import okhttp3.*;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ChatgptTest {

    private static final String API_ENDPOINT = "https://api.openai.com/v1/chat/completions";
    private static final String API_KEY = "";

    public static void main(String[] args) throws IOException {
        testChat();
//        while (true){
//            Scanner scanner = new Scanner(System.in);
//            System.out.print("ask: ");
//            String userText = scanner.nextLine();
//            if("stop".equals(userText)){
//                System.out.println("quit wait");
//                return;
//            }
//
//            String response = getChatGPTResponse(userText);
//            System.out.println("response: " + response);
//        }

//        ChatRequest chatRequest = new ChatRequest();
//        chatRequest.setModel("text-davinci-003");
//        chatRequest.setPrompt("我是你爹");
//        chatRequest.setMaxTokens(1024);
//        chatRequest.setTemperature(0);
//        System.out.println();
//        String result2 = cn.hutool.http.HttpRequest.post("https://api.openai.com/v1/completions")
//                .header(Header.CONTENT_TYPE, "application/json")//头信息，多个头信息多次调用此方法即可
//                .header(Header.AUTHORIZATION, "Bearer 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")
//                .body(JSONObject.toJSONString(chatRequest))
//                .timeout(20000) //超时，毫秒
//               .execute().body();
//        System.out.println(result2);
    }

//    private static String getChatGPTResponse(String userText) throws IOException {
//        ChatRequest chatRequest = new ChatRequest();
//        chatRequest.setModel("text-davinci-003");
//        chatRequest.setPrompt(userText);
//        chatRequest.setMaxTokens(1024);
//        chatRequest.setTemperature(0);
//
//
//        String result2 =
//                cn.hutool.http.HttpRequest
//                .post(API_ENDPOINT)
//                .header(Header.CONTENT_TYPE, "application/json")
//                .header(Header.AUTHORIZATION, API_KEY)
//                .body(JSONObject.toJSONString(chatRequest))
//                .timeout(20000)//超时，毫秒
//                .execute().body();
//
//        ChatResponse chatResponse = JSONObject.parseObject(result2, ChatResponse.class);
//
//        if(!CollectionUtils.isEmpty(chatResponse.getChoices())){
//            return chatResponse.getChoices().get(0).getText().replaceAll("\n","");
//        }
//
//        return result2;
//    }

//    private static String getChatGPTResponse(String userText) throws IOException {
//        ChatRequest chatRequest = new ChatRequest();
//        chatRequest.setModel("text-davinci-003");
//        chatRequest.setPrompt(userText);
//        chatRequest.setMaxTokens(1024);
//        chatRequest.setTemperature(0);
//
//        RestTemplate restTemplate = new RestTemplate();
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.add(Header.AUTHORIZATION.getValue(), API_KEY);
//        headers.add("Content-Type", "application/json; charset=utf-8");
//        Map map = JSONObject.parseObject(JSONObject.toJSONString(chatRequest), Map.class);
//        HttpEntity<Map<String, Object>> formEntity = new HttpEntity<>(map, headers);
//        String result2 = restTemplate.postForObject(API_ENDPOINT, formEntity, String.class);
//
////        restTemplate.postForObject(API_ENDPOINT,chatRequest,String.class);
////
////
////        String result2 =
////                cn.hutool.http.HttpRequest
////                        .post(API_ENDPOINT)
////                        .header(Header.CONTENT_TYPE, "application/json")
////                        .header(Header.AUTHORIZATION, API_KEY)
////                        .body(JSONObject.toJSONString(chatRequest))
////                        .timeout(20000)//超时，毫秒
////                        .execute().body();
//
//        ChatResponse chatResponse = JSONObject.parseObject(result2, ChatResponse.class);
//
//        if(!CollectionUtils.isEmpty(chatResponse.getChoices())){
//            return chatResponse.getChoices().get(0).getText().replaceAll("\n","");
//        }
//
//        return result2;
//    }


    private static String getChatGPTResponse(String userText) throws IOException {
        ChatRequest chatRequest = new ChatRequest();
        chatRequest.setModel("text-davinci-003");
        chatRequest.setPrompt(userText);
        chatRequest.setMaxTokens(1024);
        chatRequest.setTemperature(0);

        OkHttpClient.Builder  client =  new OkHttpClient.Builder();
        OkHttpClient okHttpClient = client.build();

        Request builder = new Request.Builder()
                .url(API_ENDPOINT)
                .post(RequestBody.create(MediaType.get("application/json"), JSONObject.toJSONString(chatRequest)))
                .addHeader(Header.AUTHORIZATION.getValue(), API_KEY)
                .build()
                ;

        Response response = okHttpClient.newCall(builder).execute();

        assert response.body() != null;

        String result2 = response.body().string();

        ChatResponse chatResponse = JSONObject.parseObject(result2, ChatResponse.class);

        if(!CollectionUtils.isEmpty(chatResponse.getChoices())){
            return chatResponse.getChoices().get(0).getText().replaceAll("\n","");
        }

        return result2;
    }

    public static void testChat() throws IOException {
        CreateChatFinishReq createChatFinishReq = new CreateChatFinishReq();
        createChatFinishReq.setModel(Model.MODEL_GPT_TURBO.getName());
        List<CreateChatFinishReq.MessagesDTO> reqList = new ArrayList<>();
        CreateChatFinishReq.MessagesDTO messagesDTO = new CreateChatFinishReq.MessagesDTO();
        messagesDTO.setRole(Role.SYSTEM.getName());
        messagesDTO.setContent("You are a helpful assistant.");
        CreateChatFinishReq.MessagesDTO messagesDTO1 = new CreateChatFinishReq.MessagesDTO();
        messagesDTO1.setRole(Role.USER.getName());
        messagesDTO1.setContent("Who won the world series in 2020?");
        CreateChatFinishReq.MessagesDTO messagesDTO3 = new CreateChatFinishReq.MessagesDTO();
        messagesDTO3.setRole(Role.ASSISTANT.getName());
        messagesDTO3.setContent("The Los Angeles Dodgers won the World Series in 2020.");
        CreateChatFinishReq.MessagesDTO messagesDTO4 = new CreateChatFinishReq.MessagesDTO();
        messagesDTO4.setRole(Role.ASSISTANT.getName());
        messagesDTO4.setContent("Where was it played?");
        reqList.add(messagesDTO);
        reqList.add(messagesDTO1);
        reqList.add(messagesDTO3);
        reqList.add(messagesDTO4);
        createChatFinishReq.setMessages(reqList);

        OkHttpClient.Builder  client =  new OkHttpClient.Builder();
        OkHttpClient okHttpClient = client.build();

        Request builder = new Request.Builder()
                .url(API_ENDPOINT)
                .post(RequestBody.create(MediaType.get("application/json"), JSONObject.toJSONString(createChatFinishReq)))
                .addHeader(Header.AUTHORIZATION.getValue(), API_KEY)
                .build()
                ;

        Response response = okHttpClient.newCall(builder).execute();

        assert response.body() != null;

        String result2 = response.body().string();
        System.out.println(result2);
    }
}
