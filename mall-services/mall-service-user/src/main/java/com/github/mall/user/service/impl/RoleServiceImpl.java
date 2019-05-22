package com.github.mall.user.service.impl;

import com.github.mall.common.dto.Result;
import com.github.mall.user.model.Role;
import com.github.mall.user.mapper.RoleMapper;
import com.github.mall.user.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

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

    /**
     * create by: HPC
     * description: 通过用户ID获取用户角色
     * create time: 2019/5/22
     */
    public Result<List<Role>> getUserRole(Long userId) {
        /*获取用户角色*/
        List<Role> userRole = baseMapper.getUserRole(userId);
        return Result.success(userRole);
    }
}
