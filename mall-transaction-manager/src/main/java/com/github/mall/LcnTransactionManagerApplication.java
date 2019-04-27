package com.github.mall;

import com.codingapi.txlcn.tm.config.EnableTransactionManagerServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 描述:
 * 事务管理器
 *
 * @author HPC
 * @create 2019-04-27
 */
@SpringBootApplication
@EnableTransactionManagerServer
public class LcnTransactionManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(LcnTransactionManagerApplication.class, args);
    }
}
