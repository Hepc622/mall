package com.github.mall.user.rpc;

import com.github.mall.user.rpc.back.ServiceProductApiFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 描述:
 * 用户模块api
 *
 * @author HPC
 * @create 2019-04-22
 */
@FeignClient(value = "SERVICE-PRODUCT", path = "/user/rpc", fallback = ServiceProductApiFallBack.class)
@Service
public interface ServiceProductApi {
    @PostMapping("/getProduct")
    void getProduct();
}
