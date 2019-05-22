package com.github.mall.user.service;

import com.github.mall.common.dto.Result;
import com.github.mall.user.model.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.awt.image.DataBufferByte;
import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author HPC
 * @since 2019-05-21
 */
public interface IRoleService extends IService<Role> {

    /**
     * create by: HPC
     * description: 通过用户ID获取用户角色
     * create time: 2019/5/22
     *
     * @return
     */
    Result<List<Role>> getUserRole(Long userID);
}
