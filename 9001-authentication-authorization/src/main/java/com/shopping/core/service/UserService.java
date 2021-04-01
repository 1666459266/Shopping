package com.shopping.core.service;

import com.shopping.core.entity.User;

public interface UserService {
    //根据用户名查询用户
    User selectUserByUserName(String username);
}
