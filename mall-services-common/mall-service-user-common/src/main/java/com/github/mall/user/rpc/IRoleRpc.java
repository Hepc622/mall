package com.github.mall.user.rpc;

import com.github.mall.user.model.Role;
import com.github.mall.user.rpc.back.RoleRpcImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author ：HPC
 * @date ：Created in 2019-05-22
 * @description：
 */
@FeignClient(value = "USER-SERVER", path = "/role/rpc", fallback = RoleRpcImpl.class)
@Component
public interface IRoleRpc {

    /**
     * create by: HPC
     * description: 通过用户ID获取用户的所有角色
     * create time: 2019/5/22
     */
    @RequestMapping(value = "/getUserRole/{userId}", method = {RequestMethod.POST})
    List<Role> getUserRole(@PathVariable("userId") Long id);
}
