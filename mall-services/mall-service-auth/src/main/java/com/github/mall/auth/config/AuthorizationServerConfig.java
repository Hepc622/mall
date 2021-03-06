package com.github.mall.auth.config;

import com.github.mall.auth.service.MallRedisTokenStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;

/**
 * @author : HPC
 * @description :  权限验证服务
 * @date : 2019/7/2 17:09
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ClientDetailsService clientDetails;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private MallRedisTokenStore redisTokenStore;


    /**
     * @param security :
     * @return void
     * @description : 设置权限拦截
     * @author : HPC
     * @date : 2019/7/2 17:07
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        /*校验token和获取token的通通放行*/
        security.checkTokenAccess("permitAll()");
        security.tokenKeyAccess("permitAll()");
        security.allowFormAuthenticationForClients();
    }

    /**
     * @param clients :
     * @return void
     * @description : 设置oauth的client用户
     * @author : HPC
     * @date : 2019/7/2 17:06
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetails);
    }

    /**
     * @param endpoints :
     * @return void
     * @description : 设置权限管理
     * @author : HPC
     * @date : 2019/7/2 17:06
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager);
        endpoints.setClientDetailsService(clientDetails);
        endpoints.userDetailsService(userDetailsService);
        endpoints.tokenStore(redisTokenStore);
    }
}