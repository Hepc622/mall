package com.github.mall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.mall.common.dto.Result;
import com.github.mall.common.utils.MallUtils;
import com.github.mall.product.model.User;
import com.github.mall.product.mapper.UserMapper;
import com.github.mall.product.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author HPC
 * @since 2019-04-26
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    /**
     * 通过参数获取用户
     *
     * @param param openid userid phone email
     * @return
     */
    public Result<User> getUserWithParam(String param) {
        User user;
        QueryWrapper<User> wrapper = new QueryWrapper<User>();

        if (MallUtils.isEmail(param)) {/*是否为通过邮箱获取*/
            wrapper.eq("email", param);
            user = baseMapper.selectOne(wrapper);
            return Result.success(user);

        } else if (MallUtils.isPhoneLegal(param, true)) {/*通过手机号码获取*/
            wrapper.eq("phone", param);
            user = baseMapper.selectOne(wrapper);
            return Result.success(user);

        } else {
            wrapper.eq("open_id", param);
            user = baseMapper.selectOne(wrapper);

            //返回空说明没有对应数据
            if (user != null) {
                return Result.success(user);
            } else {
                return Result.fail();
            }
        }

    }

}
