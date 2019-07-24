package com.github.mall.user.controller;

import com.github.mall.common.dto.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述:
 * 用户控制器
 *
 * @author HPC
 * @create 2019-04-22
 */
@RestController
@RequestMapping("/user")
public class UserController {


    @RequestMapping(value = "/getUser")
    public Result getUser() {
        return Result.success();
    }

}
