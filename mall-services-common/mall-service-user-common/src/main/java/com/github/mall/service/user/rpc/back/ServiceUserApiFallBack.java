package com.github.mall.service.user.rpc.back;

import com.github.mall.service.user.rpc.ServiceUserApi;
import org.springframework.stereotype.Service;

/**
 * 描述:
 *
 * @author HPC
 * @create 2019-04-22
 */
@Service
public class ServiceUserApiFallBack implements ServiceUserApi {
    public void getUserWithId(Long userId) {
    }
}
