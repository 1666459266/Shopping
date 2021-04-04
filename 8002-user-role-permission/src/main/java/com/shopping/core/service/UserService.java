package com.shopping.core.service;

import com.shopping.core.entity.User;

import java.util.List;

public interface UserService {
    int insertUser(User user);

    int updateUser(User user);

    User selectUserById(Integer id);

    //分页查询用户列表
    List<User> selectUserList(Integer page,Integer limit);

    //根据用户名查询用户
    User selectUserByUserName(String username);

    //修改用户状态
    int updateUserStatus(Integer id,Integer status);
}
