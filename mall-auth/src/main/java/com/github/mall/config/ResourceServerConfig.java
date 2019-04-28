//package com.github.mall.mybatis;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.mybatis.annotation.web.builders.HttpSecurity;
//import org.springframework.security.oauth2.mybatis.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.mybatis.annotation.web.configuration.ResourceServerConfigurerAdapter;
//
//@Configuration
//@EnableResourceServer
//public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
//
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .exceptionHandling()
//                .and()
//                .authorizeRequests()
//                .anyRequest().authenticated()
//                .and()
//                .httpBasic();
//    }
//}