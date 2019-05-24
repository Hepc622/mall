package com.github.mall.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.mall.user.model.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author HPC
 * @since 2019-05-21
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * create by: HPC
     * description: 通过用户ID获取用户角色
     * create time: 2019/5/22
     *
     * @return
     */
    List<Role> getUserRole(@Param("userId") Long userId);
}
