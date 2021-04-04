package com.shopping.core.service;

import com.shopping.core.entity.UserRole;

public interface UserRoleService {
    int insertUserRole(UserRole userRole);

    int updateUserRole(UserRole userRole);

    int deleteUserRoleById(Integer id);

    UserRole selectUserRoleById(Integer id);
}
