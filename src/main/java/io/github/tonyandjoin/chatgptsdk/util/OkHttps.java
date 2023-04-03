package io.github.tonyandjoin.chatgptsdk.util;

import com.alibaba.fastjson.JSONObject;
import io.github.tonyandjoin.chatgptsdk.bo.ImageEditFileDTO;
import io.github.tonyandjoin.chatgptsdk.exception.SdkException;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.apache.commons.collections4.MapUtils;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

/**
 * @author liansx
 */
@Slf4j
public class OkHttps {

    static OkHttpClient.Builder builder = new OkHttpClient.Builder()
            .readTimeout(60L,TimeUnit.SECONDS)
            .writeTimeout(60L,TimeUnit.SECONDS)
            .connectTimeout(60L,TimeUnit.SECONDS);

    public static String post(String url, String body, Map<String, String> header) {
        OkHttpClient okHttpClient = builder.build();

        Request.Builder builder = new Request.Builder()
                .post(RequestBody.create(MediaType.get("application/json"), body))
                .url(url);
        if (!MapUtils.isEmpty(header)) {
            for (Map.Entry<String, String> entry : header.entrySet()) {
                builder.addHeader(entry.getKey(), entry.getValue());
            }
        }
        Call call = okHttpClient.newCall(builder.build());
        try {
            log.info("POST请求:{} {}", url, body);
            Response response = call.execute();
            assert response.body() != null;
            log.info("POST结果:{}", JSONObject.toJSONString(response));
            return response.body().string();
        } catch (IOException e) {
            log.error("请求异常：{}", url, e);
            throw new SdkException(e.getMessage());
        }
    }

    public static String postFrom(String url, String body, Map<String, String> header, ImageEditFileDTO... files) {
        OkHttpClient okHttpClient = builder.build();

        MultipartBody.Builder bodyBuilder= new MultipartBody.Builder().setType(MultipartBody.FORM);

        Map map = JSONObject.parseObject(body, Map.class);

        map.forEach((k,v)->bodyBuilder.addFormDataPart(k.toString(),v.toString()));

        Stream.of(files).forEach(t->bodyBuilder.addFormDataPart(t.getFileName(),t.getFile().getName(),RequestBody.create(MediaType.parse("application/octet-stream"),t.getFile())));

        Request.Builder builder = new Request.Builder()
                .post(bodyBuilder.build())
                .url(url);

        if (!MapUtils.isEmpty(header)) {
            for (Map.Entry<String, String> entry : header.entrySet()) {
                builder.addHeader(entry.getKey(), entry.getValue());
            }
        }
        Call call = okHttpClient.newCall(builder.build());
        try {
            log.info("POST-FORM请求:{} {}", url, body);
            Response response = call.execute();
            assert response.body() != null;
            log.info("POST-FORM结果:{}", JSONObject.toJSONString(response));
            return response.body().string();
        } catch (IOException e) {
            log.error("请求异常：{}", url, e);
            throw new SdkException(e.getMessage());
        }
    }

    public static String get(String url, Map<String, String> header) {

        OkHttpClient okHttpClient = builder.build();

        Request.Builder builder = new Request.Builder()
                .get()
                .url(url);

        if (!MapUtils.isEmpty(header)) {
            for (Map.Entry<String, String> entry : header.entrySet()) {
                builder.addHeader(entry.getKey(), entry.getValue());
            }
        }
        try {
            log.info("GET请求:{}", url);
            Response response = okHttpClient.newCall(builder.build()).execute();
            log.info("GET请求结果：{}", JSONObject.toJSONString(response));
            assert response.body() != null;
            return response.body().string();
        } catch (IOException e) {
            log.error("请求异常：{}", url, e);
            throw new SdkException("http Get请求异常");
        }
    }

    public static String delete(String url, Map<String, String> header) {

        OkHttpClient okHttpClient = builder.build();

        Request.Builder builder = new Request.Builder()
                .delete()
                .url(url);

        if (!MapUtils.isEmpty(header)) { header.forEach(builder::addHeader); }

        try {
            log.info("delete请求:{}", url);
            Response response = okHttpClient.newCall(builder.build()).execute();
            log.info("delete请求结果：{}", JSONObject.toJSONString(response));
            assert response.body() != null;
            return response.body().string();
        } catch (IOException e) {
            log.error("请求异常：{}", url, e);
            throw new SdkException("http delete请求异常");
        }
    }
}
