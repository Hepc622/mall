package com.github.mall.rpc;

import com.github.mall.service.IProductService;
import com.github.mall.service.product.rpc.ServiceProductApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述:
 * rpc
 *
 * @author HPC
 * @create 2019-04-27
 */
@RestController
@RequestMapping("/product/rpc")
public class ProductRpc implements ServiceProductApi {
    @Autowired
    private IProductService productService;

    @Transactional
    @PostMapping("/getProduct")
    public void getProduct() {

    }
}
