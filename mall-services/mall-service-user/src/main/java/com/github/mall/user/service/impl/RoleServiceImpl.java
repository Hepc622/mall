package com.github.mall.user.service.impl;

import com.github.mall.service.user.model.Role;
import com.github.mall.user.mapper.RoleMapper;
import com.github.mall.user.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author HPC
 * @since 2019-05-21
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
