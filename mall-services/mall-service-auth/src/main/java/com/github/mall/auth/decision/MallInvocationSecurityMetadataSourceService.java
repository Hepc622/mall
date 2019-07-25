package com.github.mall.auth.decision;

import com.github.mall.user.rpc.IPermissionRpc;
import com.github.mall.user.vo.RolePermissionOutVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * create by: HPC
 * description: 权限数据源
 * create time: 2019/5/21
 * @author HPC
 */
//@Service
//@Primary
public class MallInvocationSecurityMetadataSourceService implements
        FilterInvocationSecurityMetadataSource {

    @Autowired
    private IPermissionRpc iPermissionRpc;

    private HashMap<String, Collection<ConfigAttribute>> map = null;

    /**
     * 加载权限表中所有权限
     */
    private void loadResourceDefine() {
        map = new HashMap<>(20);
        Collection<ConfigAttribute> attributes;
        ConfigAttribute configAttribute;
        List<RolePermissionOutVo> allPermission = iPermissionRpc.getAllPermission();

        for (RolePermissionOutVo permission : allPermission) {
            /*获取url已需要的权限集合*/
            attributes = map.get(permission.getUrl());
            /*如果没有权限就创建一个新的权限集合*/
            if (attributes == null) {
                attributes = new ArrayList<>();
            }

            configAttribute = new SecurityConfig(permission.getRoleName());
            attributes.add(configAttribute);
            //用权限的getUrl() 作为map的key，用ConfigAttribute的集合作为 value，
            map.put(permission.getUrl(), attributes);
        }

    }

    /**
     * @description : 此方法是为了判定用户请求的url 是否在权限表中，如果在权限表中，则返回给 decide 方法，用来判定用户是否有此权限。如果不在权限表中则放行。
     * @author : HPC
     * @date : 2019/7/2 16:26
     * @param object :
     * @return java.util.Collection<org.springframework.security.access.ConfigAttribute>
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        if (map == null) {
            loadResourceDefine();
        }
        //object 中包含用户请求的request 信息
        HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
        AntPathRequestMatcher matcher;
        String resUrl;
        for (String s : map.keySet()) {
            resUrl = s;
            matcher = new AntPathRequestMatcher(resUrl);
            if (matcher.matches(request)) {
                return map.get(resUrl);
            }
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}