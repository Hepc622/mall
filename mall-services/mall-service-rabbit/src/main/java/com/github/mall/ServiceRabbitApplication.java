package com.github.mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 描述:
 * 用户模块
 *
 * @author HPC
 * @create 2019-04-22
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ServiceRabbitApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceRabbitApplication.class, args);
    }
}
