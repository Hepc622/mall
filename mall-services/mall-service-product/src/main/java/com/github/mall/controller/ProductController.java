package com.github.mall.controller;

import com.github.mall.entity.Product;
import com.github.mall.service.IProductService;
import com.github.mall.service.user.rpc.ServiceUserApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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
    @Autowired
    private ServiceUserApi serviceUserApi;
    @Autowired
    private IProductService productService;

    @PostMapping("/test/{id}")
    public void test(@PathVariable("id") Long id) {
        Product product = productService.getById(1);
        product.setProPrice(product.getProPrice().subtract(new BigDecimal(1)));
        productService.updateById(product);
        serviceUserApi.getUserWithId(id);
    }
}
