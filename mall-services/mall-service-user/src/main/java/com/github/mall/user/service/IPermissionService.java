package com.github.mall.user.service;

import com.github.mall.common.dto.Result;
import com.github.mall.user.model.Permission;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.mall.user.vo.RolePermissionOutVo;

import java.awt.image.RenderedImage;
import java.util.List;

/**
 * <p>
 * 权限表 服务类
 * </p>
 *
 * @author HPC
 * @since 2019-05-21
 */
public interface IPermissionService extends IService<Permission> {

    /**
     * create by: HPC
     * description: 获取权限对应的角色
     * create time: 2019/5/22
     */
    Result<List<RolePermissionOutVo>> getAllPermission();
}
