//package com.github.mall.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
//
//@Configuration
//@EnableAuthorizationServer
//public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
//    @Autowired
//    private AuthenticationManager authenticationManager;
////    @Autowired
////    private RedisConnectionFactory connectionFactory;
//
//
////    @Bean
////    public RedisTokenStore tokenStore() {
////        return new RedisTokenStore(connectionFactory);
////    }
//
//
//    @Override
//    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//        endpoints
//                .authenticationManager(authenticationManager);
////                .tokenStore(tokenStore());
//    }
//
//    @Override
//    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
//        security
//                .tokenKeyAccess("permitAll()")
//                .checkTokenAccess("isAuthenticated()");
//    }
//
//    @Override
//    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients.inMemory()
//                .withClient("phone")
//                .scopes("app") //此处的scopes是无用的，可以随意设置
//                .secret("android")
//                .authorizedGrantTypes("password", "authorization_code", "refresh_token")
//                .and()
//                .withClient("webapp")
//                .scopes("web")
//                .authorizedGrantTypes("implicit");
//    }
//}