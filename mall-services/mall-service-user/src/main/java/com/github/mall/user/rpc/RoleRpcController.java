package com.github.mall.user.rpc;

import com.github.mall.user.model.Role;
import com.github.mall.user.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ：HPC
 * @date ：Created in 2019-05-22
 * @description： 角色获取RPC
 */
@RestController
@RequestMapping("/role/rpc")
@Transactional(rollbackFor = Exception.class)
public class RoleRpcController implements IRoleRpc {

    @Autowired
    private IRoleService iRoleService;

    /**
     * create by: HPC
     * description: 通过用户ID获取用户的所有角色
     * create time: 2019/5/22
     */
    @RequestMapping(value = "/getUserRole/{userId}", method = {RequestMethod.POST})
    public List<Role> getUserRole(@PathVariable("userId") Long userId) {
        return iRoleService.getUserRole(userId).getData();
    }

}
