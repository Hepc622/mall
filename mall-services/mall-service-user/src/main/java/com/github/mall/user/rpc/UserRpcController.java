package com.github.mall.user.rpc;

import com.github.mall.user.model.User;
import com.github.mall.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
@Transactional(rollbackFor = Exception.class)
public class UserRpcController implements IUserRpc {

    @Autowired
    private IUserService userService;

    /**
     * 通过参数获取user
     *
     * @param param openId userId phoneNo email
     * @return
     */
    @Override
    @PostMapping("/getUserWithParam/{param}")
    public User getUserWithParam(@PathVariable("param") String param) {
        return userService.getUserWithParam(param).getData();
    }

}
