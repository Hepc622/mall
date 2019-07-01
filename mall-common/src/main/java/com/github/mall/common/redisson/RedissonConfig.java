package com.github.mall.common.redisson;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.util.ResourceUtils;

import java.io.IOException;

/**
 * 描述: redisson配置
 *
 * @author HPC
 * @create 2019-04-28
 */
@Configuration
public class RedissonConfig {

    @Bean
    @Primary
    public RedissonClient redissonClient() throws IOException {
        Config config = Config.fromJSON(ResourceUtils.getFile("classpath:redisson.json"));
        return Redisson.create(config);
    }
}
