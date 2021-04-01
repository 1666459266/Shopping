package com.shopping.core.dao;

import com.shopping.core.entity.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User user);

    int insertSelective(User user);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User user);

    int updateByPrimaryKey(User user);

    //根据用户名查询用户
    User selectUserByUserName(String username);
}