package com.github.mall.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.mall.user.model.Permission;
import com.github.mall.user.vo.RolePermissionOutVo;

import java.util.List;

/**
 * <p>
 * 权限表 Mapper 接口
 * </p>
 *
 * @author HPC
 * @since 2019-05-21
 */
public interface PermissionMapper extends BaseMapper<Permission> {

    /**
     * create by: HPC
     * description: 获取权限对应的角色
     * create time: 2019/5/22
     * @Param: null
     * @return 
     */
    List<RolePermissionOutVo> getAllPermission();
}
