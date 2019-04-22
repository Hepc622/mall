package com.github.mall.rpc;

import com.github.mall.service.user.rpc.ServiceUserApi;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述:
 * 内部调用
 *
 * @author HPC
 * @create 2019-04-22
 */
@RestController
@RequestMapping("/user")
public class UserRpc implements ServiceUserApi {

    @PostMapping("/getUser")
    public void getUserWithId(@RequestBody Long userId) {

    }
}
