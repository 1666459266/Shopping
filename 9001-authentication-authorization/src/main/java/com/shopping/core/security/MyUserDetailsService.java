package com.shopping.core.security;

import com.shopping.core.entity.User;
import com.shopping.core.service.PermissionService;
import com.shopping.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired(required = false)
    private UserService userService;
    @Autowired(required = false)
    private PermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.selectUserByUserName(username);
        if (user == null)
            return null;
        //将permissions转化为数组
        List<String> permissions = permissionService.selectPermissionByUserName(username);
        String[] permissionArray = new String[permissions.size()];
        permissions.toArray(permissionArray);
        //返回userDetails
        UserDetails userDetails = org.springframework.security.core.userdetails.User
                .withUsername(user.getUserName())
                .password(user.getPassword())
                .authorities(permissionArray)
                .build();
        return userDetails;
    }
}
