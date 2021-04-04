package com.shopping.core.service;

import com.shopping.core.entity.RolePermission;

public interface RolePermissionService {
    int insertRolePermission(RolePermission rolePermission);

    int updateRolePermission(RolePermission rolePermission);

    int deleteRolePermissionById(Integer id);

    RolePermission selectRolePermissionById(Integer id);
}
