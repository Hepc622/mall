package com.github.mall.auth.rpc.back;

import com.github.mall.auth.rpc.IAuthRpc;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Author HPC
 * @Description Auth远程调用实现
 * @Date 2019/7/4 15:53
 */
@Service
public class AuthRpcImpl implements IAuthRpc {
    @Override
    public Map<String, ?> checkToken(String value) {
        return null;
    }
}
