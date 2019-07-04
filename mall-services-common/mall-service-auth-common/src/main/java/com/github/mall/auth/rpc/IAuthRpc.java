package com.github.mall.auth.rpc;

import com.github.mall.auth.rpc.back.AuthRpcImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @Author HPC
 * @Description 权限内部调用Rpc
 * @Date 2019/7/4 15:52
 */
@FeignClient(name = "SERVICE-AUTH", fallback = AuthRpcImpl.class)
public interface IAuthRpc {

    @RequestMapping(value = "/oauth/check_token", method = {RequestMethod.POST})
    Map<String, ?> checkToken(@RequestParam("token") String value);
}
