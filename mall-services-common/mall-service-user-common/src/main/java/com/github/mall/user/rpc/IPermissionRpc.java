package com.github.mall.user.rpc;

import com.github.mall.user.rpc.back.PermissionRpcImpl;
import com.github.mall.user.vo.RolePermissionOutVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author ：HPC
 * @date ：Created in 2019-05-22
 * @description： 权限码rpc
 */
@FeignClient(name = "SERVICE-USER", path = "/permission/rpc", fallback = PermissionRpcImpl.class)
public interface IPermissionRpc {


    /**
     * create by: HPC
     * description: 获取所有的权限
     * create time: 2019/5/22
     *
     * @return
     */
    @RequestMapping(value = "/getAllPermission", method = {RequestMethod.POST})
    List<RolePermissionOutVo> getAllPermission();
}
