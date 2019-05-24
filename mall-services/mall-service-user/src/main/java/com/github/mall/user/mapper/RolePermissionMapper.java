package com.github.mall.user.mapper;

import com.github.mall.user.model.RolePermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 角色权限关系表 Mapper 接口
 * </p>
 *
 * @author HPC
 * @since 2019-05-21
 */
@Mapper
public interface RolePermissionMapper extends BaseMapper<RolePermission> {

}
