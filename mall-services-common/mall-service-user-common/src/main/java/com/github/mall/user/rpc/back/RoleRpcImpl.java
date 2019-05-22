package com.github.mall.user.rpc.back;

import com.github.mall.user.model.Role;
import com.github.mall.user.rpc.IRoleRpc;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：HPC
 * @date ：Created in 2019-05-22
 * @description：
 */
@Service
public class RoleRpcImpl implements IRoleRpc {
    @Override
    public List<Role> getUserRole(Long id) {
        return null;
    }
}
