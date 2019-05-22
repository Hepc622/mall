package com.github.mall.user.rpc.back;

import com.github.mall.common.dto.Result;
import com.github.mall.user.model.Permission;
import com.github.mall.user.rpc.IPermissionRpc;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：HPC
 * @date ：Created in 2019-05-22
 * @description：
 */
@Service
public class PermissionRpcImpl implements IPermissionRpc {

    public Result<List<Permission>> getAllPermission() {
        return null;
    }
}
