package com.github.mall.user.rpc.back;

import com.github.mall.user.rpc.IPermissionRpc;
import com.github.mall.user.vo.RolePermissionOutVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：HPC
 * @date ：Created in 2019-05-22
 * @description：
 */
@Service
public class PermissionRpcImpl implements IPermissionRpc {

    @Override
    public List<RolePermissionOutVo> getAllPermission() {
        return null;
    }
}
