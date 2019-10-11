package com.github.mall.transaction;

import com.codingapi.txlcn.tm.config.EnableTransactionManagerServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ：HPC
 * @date ：Created in 2019-10-11
 * @description： 分布式事务管理器
 */
@SpringBootApplication
@EnableTransactionManagerServer
public class MallTransactionApplication {
    public static void main(String[] args) {
        SpringApplication.run(MallTransactionApplication.class, args);
    }
}
