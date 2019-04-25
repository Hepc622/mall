package com.github.mall.rpc;

import com.github.mall.service.user.rpc.ServiceUserApi;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/getUser")
    public void getUserWithId(@RequestBody Long userId) {
        System.out.println("111111111111111");
    }
    @GetMapping("/getUser2")
    public void getUserWithId() {
        System.out.println("111111111111111");
    }
}
