package com.github.mall.service.impl;

import com.github.mall.entity.Product;
import com.github.mall.mapper.ProductMapper;
import com.github.mall.service.IProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author HPC
 * @since 2019-04-26
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

}
