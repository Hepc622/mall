package com.github.mall.auth.service;

import com.github.mall.common.utils.MallUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author ：HPC
 * @date ：Created in 2019-05-20
 * @description： 用户详情实现
 */
@Component
@Primary
public class MallUserDetailsServiceIml implements UserDetailsService {

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = null;

        //手机号码登录
        if (MallUtils.isPhoneLegal(username, true)) {
            userDetails = loginWithPhoneNo(username);
        } else {//openId登录
            userDetails = loginWithOpenId(username);
        }

        /*判断是否为空*/
        if (userDetails == null) {
            return null;
        }

        /*设置用户权限*/
        setGrantAuthority(userDetails);

        return userDetails;
    }

    private void setGrantAuthority(UserDetails userDetails) {
        //TODO 获取用户对应的角色和权限
        List<String> right = new ArrayList<>();
        right.add("");


        /*处理权限*/
        Collection<GrantedAuthority> collection = (Collection<GrantedAuthority>) userDetails.getAuthorities();
        /*如果等于空就给一个空容器*/
        if (collection == null) {
            collection = new ArrayList<>();
        }
        /*添加权限*/
        for (String role : right) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_" + role);
            collection.add(grantedAuthority);
        }
    }

    /**
     * create by: HPC
     * description: 通过openID登录系统
     * create time: 2019/5/20
     *
     * @return
     */
    private UserDetails loginWithOpenId(String openId) {
        return null;
    }

    /**
     * create by: HPC
     * description: 通过手机号码登录
     * create time: 2019/5/20
     *
     * @return
     * @Param: null
     */
    private UserDetails loginWithPhoneNo(String phoneNo) {
        return null;
    }

    /**
     * create by: HPC
     * description: 通过账号密码登录
     * create time: 2019/5/20
     *
     * @return
     */
    private UserDetails loginWithEmail(String userName) {
        return null;
    }

}
