package com.github.mall.user.service.impl;

import com.github.mall.service.user.model.Permission;
import com.github.mall.user.mapper.PermissionMapper;
import com.github.mall.user.service.IPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author HPC
 * @since 2019-05-21
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

}
