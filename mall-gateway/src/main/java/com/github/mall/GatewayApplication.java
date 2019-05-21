package com.github.mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

//import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;

/**
 * 描述:
 * 网关启动器
 *
 * @author HPC
 * @create 2019-04-20
 */
@EnableDiscoveryClient
@SpringBootApplication
//@EnableOAuth2Sso
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
}
