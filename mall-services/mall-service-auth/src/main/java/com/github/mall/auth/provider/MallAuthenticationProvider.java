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
public class MallAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private UserDetailsService userDetailsService;

    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UserDetails userDetails = userDetailsService.loadUserByUsername(authentication.getPrincipal().toString());
        if (userDetails != null) {
            return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword());
        }
        return null;
    }

    public boolean supports(Class<?> authentication) {
        return true;
    }
}
