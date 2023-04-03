package io.github.tonyandjoin.chatgptsdk.config;

import io.github.tonyandjoin.chatgptsdk.auth.AuthClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liansx
 */

@Configuration
@ConfigurationProperties(prefix = "openai")
@EnableConfigurationProperties(OpenAiConfig.class)
public class OpenAiConfig {

    private String baseUrl;

    private String appKey;

    private String proxyIp;

    private Integer proxyPort;

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getProxyIp() {
        return proxyIp;
    }

    public void setProxyIp(String proxyIp) {
        this.proxyIp = proxyIp;
    }

    public Integer getProxyPort() {
        return proxyPort;
    }

    public void setProxyPort(Integer proxyPort) {
        this.proxyPort = proxyPort;
    }

    @Bean
    public AuthClient authClient(){
        AuthClient client = new AuthClient(appKey, baseUrl, proxyIp, proxyPort);
        return client;
    }
}
