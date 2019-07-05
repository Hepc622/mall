package com.github.mall.auth.service;

import com.github.mall.user.model.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @author ：HPC
 * @date ：Created in 2019-05-22
 * @description： 用户详情
 */
@EqualsAndHashCode(callSuper = true)
@Data
public
class MallUserDetails extends User implements UserDetails {
    private Collection<? extends GrantedAuthority> authorities;
    private String password;
    private String username;
    private boolean accountNonExpired = true;
    private boolean credentialsNonExpired = true;
    private boolean enabled = true;
    private boolean accountNonLocked = true;
}
