package com.github.mall.user.vo;

import com.github.mall.user.model.RolePermission;
import lombok.Data;

/**
 * @author ：HPC
 * @date ：Created in 2019-05-22
 * @description： 权限角色对应关系实体
 */
@Data
public class RolePermissionOutVo extends RolePermission {
    /**角色名称*/
    private String roleName;

    /**资源路径*/
    private String url;
}
