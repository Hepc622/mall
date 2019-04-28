package com.github.mall.common.fastdfs;

import com.github.mall.common.fastdfs.csource.util.FastDFSClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

/**
 * 描述:
 * fastdfsConfig
 *
 * @author HPC
 * @create 2019-04-29
 */
@Configuration
public class FastDFSConfig {
    @Bean
    @Scope(value = "prototype")
    @Primary
    public FastDFSClient fastDFSClient() throws Exception {
//        return new FastDFSClient();
        return null;
    }
}
