package com.lhj.security;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lhj.pojo.Permission;
import com.lhj.pojo.Role;
import com.lhj.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Set;

/**
 * @author lhj
 * @creat 2022-04-25-17:06
 */
@Component
public class SpringSecurityUserService implements UserDetailsService {
    @Reference
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        com.lhj.pojo.User user = userService.findUserByName(s);
        if (user == null) {
            return null;
        }
        //遍历获取角色权限集合
        ArrayList<GrantedAuthority> list = new ArrayList<>();
        Set<Role> roleSet = user.getRoles();
        for (Role role : roleSet) {
            Set<Permission> permissions = role.getPermissions();
            for (Permission permission : permissions) {
                list.add(new SimpleGrantedAuthority(permission.getKeyword()));
            }
        }
        return new User(user.getUsername(), user.getPassword(), list);
    }
}
