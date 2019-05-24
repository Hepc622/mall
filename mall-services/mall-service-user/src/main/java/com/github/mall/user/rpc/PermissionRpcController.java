package com.github.mall.user.rpc;

import com.github.mall.user.service.IPermissionService;
import com.github.mall.user.vo.RolePermissionOutVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ：HPC
 * @date ：Created in 2019-05-22
 * @description： 权限内部调用
 */
@RestController
@RequestMapping("/permission/rpc")
@Transactional(rollbackFor = Exception.class)
public class PermissionRpcController implements IPermissionRpc {

    @Autowired
    private IPermissionService iPermissionService;

    /**
     * create by: HPC
     * description: 获取所有的权限对应的role
     * create time: 2019/5/22
     * @Param: null
     * @return
     */
    @Override
    @RequestMapping(value = "/getAllPermission", method = {RequestMethod.POST})
    public List<RolePermissionOutVo> getAllPermission() {
        return iPermissionService.getAllPermission().getData();
    }
}
