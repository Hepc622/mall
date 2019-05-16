package com.github.mall.user.service.impl;

import com.github.mall.user.entity.User;
import com.github.mall.user.mapper.UserMapper;
import com.github.mall.user.service.IUserService;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
