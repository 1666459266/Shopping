package com.shopping.core.dao;

import com.shopping.core.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role role);

    int insertSelective(Role role);

    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Role role);

    int updateByPrimaryKey(Role role);

    //分页查询角色列表
    List<Role> selectRoleList(@Param("page") Integer page,@Param("limit") Integer limit);

    //修改角色状态
    int updateRoleStatus(@Param("id") Integer id,@Param("status") Integer status);
}