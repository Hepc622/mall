package com.github.mall.product.rpc.back;

import com.github.mall.product.rpc.ServiceProductApi;
import org.springframework.stereotype.Service;

/**
 * 描述:
 *
 * @author HPC
 * @create 2019-04-22
 */
@Service
public class ServiceProductApiFallBack implements ServiceProductApi {

    @Override
    public void getProduct() {

    }
}
