package com.github.mall.auth.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;

import java.util.Arrays;
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

    /**
     * client缓存map
     */
    private Map<String, BaseClientDetails> cacheClient = new HashMap<>();

    /**
     * 注入客户端信息
     */
    private List<ClientInfo> clients;


    /**
     * @return java.util.Map<java.lang.String, org.springframework.security.oauth2.provider.client.BaseClientDetails>
     * @description : 获取客户端信息缓存
     * @author : HPC
     * @date : 2019/7/2 16:23
     */
    private Map<String, BaseClientDetails> getCacheClient() {
        if (cacheClient.size() == 0) {
            for (ClientInfo clientInfo : clients) {
                /*设置作用域和权限*/
                BaseClientDetails details = new BaseClientDetails(null, null,
                        clientInfo.getScopes(), clientInfo.getGrantTypes(), clientInfo.getAuthorities(),null);

                details.setClientId(clientInfo.getClient());
                details.setClientSecret(clientInfo.getSecret());
                details.setAccessTokenValiditySeconds(clientInfo.getAccessTokenValiditySeconds());
                details.setRefreshTokenValiditySeconds(clientInfo.getRefreshTokenValiditySeconds());
                details.setAutoApproveScopes(Arrays.asList(clientInfo.getScopes().split(",")));
                /*添加到缓存中*/
                cacheClient.put(clientInfo.getClient(), details);
            }
        }
        return cacheClient;
    }

    /**
     * 获取client对应的数据
     */
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
    /**
     * 重定向uri
     */
    private String redirectUris;
    /**
     * token有效时长
     */
    private Integer accessTokenValiditySeconds = 86400;
    /**
     * 刷新token有效时长
     */
    private Integer refreshTokenValiditySeconds = 2592000;
}