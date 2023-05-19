package com.haoqi.hros.config;

import com.haoqi.hros.model.Menu;
import com.haoqi.hros.model.Role;
import com.haoqi.hros.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

@Component
public class CustomFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    @Autowired
    MenuService menuService;
    //路径比较工具
    AntPathMatcher antPathMatcher = new AntPathMatcher();
    Logger logger = Logger.getLogger("com.haoqi.hros.config.ustomFilterInvocationSecurityMetadataSource");

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        //获取当前请求路径
        String requestUrl = ((FilterInvocation) object).getRequestUrl();
        logger.warning(requestUrl);
        //获取所有的菜单url路径
        List<Menu> menus = menuService.getAllMenusWithRole();
        // AntPathMatcher，主要用来实现 ant 风格的 URL 匹配。
        for (Menu menu : menus) {
            if (antPathMatcher.match(menu.getUrl(), requestUrl)) {
                //拥有当前菜单权限的角色
                List<Role> roles = menu.getRoles();
                String[] strings = new String[roles.size()];
                for (int i = 0; i < roles.size(); i++) {
                    strings[i] = roles.get(i).getName();
                }
                return SecurityConfig.createList(strings);
            }
        }
        // 没匹配上的资源都是登录，或者为公共资源
        return SecurityConfig.createList("ROLE_LOGIN");
    }


    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}
