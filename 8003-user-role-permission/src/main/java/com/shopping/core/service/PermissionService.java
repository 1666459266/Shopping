package com.shopping.core.service;

import com.shopping.core.entity.Permission;

import java.util.List;

public interface PermissionService {
    int insertPermission(Permission permission);

    int updatePermission(Permission permission);

    Permission selectPermissionById(Integer id);

    //分页查询权限列表
    List<Permission> selectPermissionList(Integer page,Integer limit);

    //修改权限状态
    int updatePermissionStatus(Integer id,Integer status);
}
