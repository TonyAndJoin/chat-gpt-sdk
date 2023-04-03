package io.github.tonyandjoin.chatgptsdk.auth;

import io.github.tonyandjoin.chatgptsdk.client.OpenAiClient;
import io.github.tonyandjoin.chatgptsdk.client.impl.OpenAiClientImpl;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.net.InetSocketAddress;
import java.net.Proxy;

/**
 * @author liansx
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthClient {

    private String apiKey;

    private String apiBaseUrl;

    private String proxyIp;

    private Integer proxyPort;

    private Proxy proxy;


    public AuthClient(String apiKey, String apiBaseUrl, String proxyIp, Integer proxyPort) {
        if(StringUtils.isEmpty(proxyIp)){
            this.apiKey = apiKey;
            this.apiBaseUrl = apiBaseUrl;
        }else {
            this.apiKey = apiKey;
            this.apiBaseUrl = apiBaseUrl;
            this.proxyIp = proxyIp;
            this.proxyPort = proxyPort;
            this.proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyIp, proxyPort));
        }
    }

    public String getApiKey() {
        return "Bearer " + this.apiKey;
    }

    public Proxy getProxy() {
        return proxy;
    }

    public OpenAiClient getClient(){
        return new OpenAiClientImpl(this);
    }
}
