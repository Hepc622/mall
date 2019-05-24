package com.github.mall.auth.service;

import com.github.mall.common.utils.MallUtils;
import com.github.mall.user.model.Role;
import com.github.mall.user.model.User;
import com.github.mall.user.rpc.IRoleRpc;
import com.github.mall.user.rpc.IUserRpc;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：HPC
 * @date ：Created in 2019-05-20
 * @description： 用户详情实现
 */
@Service
@Primary
@Slf4j
public class MallUserDetailsServiceIml implements UserDetailsService {
    @Autowired
    private IUserRpc userRpc;

    @Autowired
    private IRoleRpc roleRpc;


    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails;

        try {
            //手机号码登录
            if (MallUtils.isPhoneLegal(username, true)) {
                userDetails = loginWithPhoneNo(username);
            } else if (MallUtils.isEmail(username)) {//邮箱登录
                userDetails = loginWithEmail(username);
            } else {//openId登录
                userDetails = loginWithOpenId(username);
            }
        } catch (Exception e) {
            log.error("用户登录抛出异常：" + e.getMessage());
            return null;
        }

        /*判断是否为空*/
        if (userDetails == null) {
            return null;
        }

        /*设置用户权限*/
        setGrantAuthority((MallUserDetails) userDetails);

        return userDetails;
    }

    /*获取用户权限并且设置权限*/
    private void setGrantAuthority(MallUserDetails user) {
        /*获取该用户有的权限*/
        List<Role> roles = roleRpc.getUserRole(user.getId());

        /*组拼角色*/
        List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>(roles.size());
        for (Role vo : roles) {
            String roleName = vo.getRoleName();
            grantedAuthorities.add(new SimpleGrantedAuthority(roleName));
        }

        /*设置权限*/
        user.setAuthorities(grantedAuthorities);
    }

    /**
     * create by: HPC
     * description: 通过openID登录系统
     * create time: 2019/5/20
     *
     * @return
     */
    private UserDetails loginWithOpenId(String openId) {
        /*获取用户信息*/
        User user = userRpc.getUserWithParam(openId);
        MallUserDetails userDetails = new MallUserDetails();
        BeanUtils.copyProperties(user, userDetails);
        /*设置用户名*/
        userDetails.setUsername(openId);
        return userDetails;
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
        /*获取用户信息*/
        MallUserDetails user = (MallUserDetails) userRpc.getUserWithParam(phoneNo);
        /*设置用户名*/
        user.setUsername(phoneNo);
        /*设置密码*/
        user.setPassword(user.getPassword());
        return user;
    }

    /**
     * create by: HPC
     * description: 通过账号密码登录
     * create time: 2019/5/20
     *
     * @return
     */
    private UserDetails loginWithEmail(String userName) {
        /*获取用户信息*/
        MallUserDetails user = (MallUserDetails) userRpc.getUserWithParam(userName);
        /*设置用户名*/
        user.setUsername(userName);
        /*设置密码*/
        user.setPassword(user.getPassword());
        return user;
    }

}
