package com.github.mall.user.rpc;

import com.github.mall.user.model.User;
import com.github.mall.user.rpc.back.UserRpcImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author ：HPC
 * @date ：Created in 2019-05-22
 * @description： 用户内部调用
 */
@FeignClient(value = "SERVICE-USER", path = "/rpc/user", fallback = UserRpcImpl.class)
public interface IUserRpc {
    /**
     * create by: HPC
     * description: 通过参数获取用户
     * create time: 2019/5/22
     */
    @RequestMapping(value = "/getUserWithParam/{param}", method = {RequestMethod.POST})
    User getUserWithParam(@PathVariable("param") String param);
}
