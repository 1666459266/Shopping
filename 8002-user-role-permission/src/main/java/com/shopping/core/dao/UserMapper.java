package com.shopping.core.dao;

import com.shopping.core.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User user);

    int insertSelective(User user);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User user);

    int updateByPrimaryKey(User user);

    //分页查询用户列表
    List<User> selectUserList(@Param("page") Integer page,@Param("limit") Integer limit);

    //根据用户名查询用户
    User selectUserByUserName(String username);

    //修改用户状态
    int updateUserStatus(@Param("id") Integer id,@Param("status") Integer status);
}