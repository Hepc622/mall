package com.github.mall.user.service.impl;

import com.github.mall.common.dto.Result;
import com.github.mall.user.model.Permission;
import com.github.mall.user.mapper.PermissionMapper;
import com.github.mall.user.service.IPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.mall.user.vo.RolePermissionOutVo;
import org.springframework.stereotype.Service;

import java.util.List;

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

    /**
     * create by: HPC
     * description: 获取所有权限对应的角色名字
     * create time: 2019/5/22
     */
    @Override
    public Result<List<RolePermissionOutVo>> getAllPermission() {
        return Result.success(baseMapper.getAllPermission());
    }
}
