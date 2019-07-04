package com.github.mall.auth.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

/**
 * @author ：HPC
 * @date ：Created in 2019-05-20
 * @description： 验证提供
 */
@Component
@Primary
public class MallAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        /*处理用户明为空的情况*/
        Object principal = authentication.getPrincipal();
        principal = principal != null ? principal : "";
        /*获取用户信息*/
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.toString());
        if (userDetails != null) {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
            authenticationToken.setDetails(userDetails);
            return authenticationToken;
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
