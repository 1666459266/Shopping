package com.shopping.core.dao;

import com.shopping.core.entity.Permission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Permission permission);

    int insertSelective(Permission permission);

    Permission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Permission permission);

    int updateByPrimaryKey(Permission permission);

    //分页查询权限列表
    List<Permission> selectPermissionList(@Param("page") Integer page,@Param("limit") Integer limit);

    //修改权限状态
    int updatePermissionStatus(@Param("id") Integer id,@Param("status") Integer status);
}