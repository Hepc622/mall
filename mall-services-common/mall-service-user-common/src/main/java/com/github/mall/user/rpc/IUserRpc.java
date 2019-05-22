package com.github.mall.user.rpc;

import com.github.mall.user.model.User;
import com.github.mall.user.rpc.back.UserRpcImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author ：HPC
 * @date ：Created in 2019-05-22
 * @description： 用户内部调用
 */
@FeignClient(value = "USER-SERVER", path = "/user/rpc", fallback = UserRpcImpl.class)
@Component
public interface IUserRpc {
    /**
     * create by: HPC
     * description: 通过参数获取用户
     * create time: 2019/5/22
     * @return
     */
    @RequestMapping(value = "/getUserWithParam/{param}", method = {RequestMethod.POST})
    User getUserWithParam(@PathVariable("param") String param);
}
