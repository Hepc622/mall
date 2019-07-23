package com.github.mall.auth.rpc;

import com.github.mall.auth.rpc.back.AuthRpcImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author HPC
 * @Description 权限内部调用Rpc
 * @Date 2019/7/4 15:52
 */
@FeignClient(value = "SERVICE-AUTH", fallback = AuthRpcImpl.class)
public interface IAuthRpc {

    /**
     * 校验token
     *
     * @param value token
     */
    @GetMapping("/oauth/check_token")
    Map<String, ?> checkToken(@RequestParam("token") String value);
}
