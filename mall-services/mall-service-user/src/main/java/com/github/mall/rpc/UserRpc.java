package com.github.mall.rpc;

import com.github.mall.entity.User;
import com.github.mall.service.IUserService;
import com.github.mall.service.user.rpc.ServiceUserApi;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/user/rpc")
public class UserRpc implements ServiceUserApi {
    @Autowired
    private IUserService userService;

    @PostMapping("/getUser")
    public void getUserWithId(@RequestBody Long userId) {
        User user = userService.getById(userId);
        user.setUserAge(user.getUserAge() - 1);
        userService.updateById(user);
        int x = 1 / 0;
    }
}
