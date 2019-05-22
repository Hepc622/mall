package com.github.mall.user.service;

import com.github.mall.common.dto.Result;
import com.github.mall.user.model.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author HPC
 * @since 2019-04-26
 */
public interface IUserService extends IService<User> {

    Result<User> getUserWithParam(String param);
}
