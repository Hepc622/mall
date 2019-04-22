package com.github.mall.service.user.rpc;

import com.github.mall.service.user.rpc.back.ServiceUserApiFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 描述:
 * 用户模块api
 *
 * @author HPC
 * @create 2019-04-22
 */
@FeignClient(value = "SERVICE-USER", path = "/uer", fallback = ServiceUserApiFallBack.class)
@Service
public interface ServiceUserApi {
    @PostMapping("/getUser")
    void getUserWithId(@RequestBody Long userId);
}
