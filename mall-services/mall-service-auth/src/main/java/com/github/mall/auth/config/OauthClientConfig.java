package com.github.mall.auth.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ：HPC
 * @date ：Created in 2019-05-28
 * @description： oauthclient 配置
 */
@Data
@Configuration
@ConfigurationProperties("oauths")
public class OauthClientConfig {

    /*client缓存map*/
    private Map<String, BaseClientDetails> cacheClient = new HashMap<>();

    private List<ClientInfo> clients;


    /*获取客户端信息缓存*/
    public Map<String, BaseClientDetails> getCacheClient() {
        if (cacheClient.size() == 0) {
            for (ClientInfo clientInfo : clients) {
                BaseClientDetails details = new BaseClientDetails(clientInfo.getClient(), "",
                        clientInfo.getScopes(), clientInfo.getGrantTypes(), clientInfo.getAuthorities(),
                        clientInfo.getRedirectUris());
                details.setClientSecret(clientInfo.getSecret());
                cacheClient.put(clientInfo.getClient(), details);
            }
        }
        return cacheClient;
    }

    /*获取client对应的数据*/
    public BaseClientDetails getClientWithId(String clientId) {
        return getCacheClient().get(clientId);
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class ClientInfo {
    private String client;
    private String secret;
    private String scopes;
    private String grantTypes;
    private String authorities;
    private String redirectUris;
}