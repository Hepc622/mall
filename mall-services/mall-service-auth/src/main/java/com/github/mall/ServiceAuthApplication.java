package com.github.mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述:
 * 验证服务
 *
 * @author HPC
 * @create 2019-04-29
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
@RestController
public class ServiceAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceAuthApplication.class, args);
    }
}


