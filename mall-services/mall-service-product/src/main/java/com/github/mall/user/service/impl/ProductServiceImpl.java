package com.github.mall.user.service.impl;

import com.github.mall.user.entity.Product;
import com.github.mall.user.mapper.ProductMapper;
import com.github.mall.user.service.IProductService;
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
