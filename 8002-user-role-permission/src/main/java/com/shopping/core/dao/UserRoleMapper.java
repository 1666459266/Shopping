package com.shopping.core.dao;

import com.shopping.core.entity.UserRole;

public interface UserRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserRole userRole);

    int insertSelective(UserRole userRole);

    UserRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserRole userRole);

    int updateByPrimaryKey(UserRole userRole);
}