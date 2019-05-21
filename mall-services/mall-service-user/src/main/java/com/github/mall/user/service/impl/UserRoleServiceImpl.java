package com.github.mall.user.service.impl;

import com.github.mall.service.user.model.UserRole;
import com.github.mall.user.mapper.UserRoleMapper;
import com.github.mall.user.service.IUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户角色关系表 服务实现类
 * </p>
 *
 * @author HPC
 * @since 2019-05-21
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

}
