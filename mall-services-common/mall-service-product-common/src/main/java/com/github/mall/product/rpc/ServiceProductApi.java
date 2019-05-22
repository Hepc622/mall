package com.github.mall.product.rpc;

import com.github.mall.product.rpc.back.ServiceProductApiFallBack;
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
@FeignClient(value = "SERVICE-PRODUCT", path = "/product/rpc", fallback = ServiceProductApiFallBack.class)
@Service
public interface ServiceProductApi {
    @PostMapping("/getProduct")
    void getProduct();
}
