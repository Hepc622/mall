package com.github.mall.product.controller;

import com.alibaba.fescar.core.context.RootContext;
import com.alibaba.fescar.spring.annotation.GlobalTransactional;
import com.github.mall.product.entity.Product;
import com.github.mall.product.service.IProductService;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author HPC
 * @since 2019-04-26
 */
@RestController
@RequestMapping("/product")
public class ProductController {
//    @Autowired
//    private ServiceUserApi serviceUserApi;
    @Autowired
    private IProductService productService;
    @Autowired
    private RedissonClient redissonClient;

    @PostMapping("/test")
    @GlobalTransactional
    @Transactional
    public void test() {

        RMap<Object, Object> map = redissonClient.getMap("");
        String xid = RootContext.getXID();
        System.out.println(xid);
        Product product = productService.getById(1);
        product.setProPrice(product.getProPrice().subtract(new BigDecimal(1)));
        productService.updateById(product);
//        serviceUserApi.getUserWithId();
    }
}
