package com.shopping.core.service;

import com.shopping.core.entity.Role;

import java.util.List;

public interface RoleService {
    int insertRole(Role role);

    int updateRole(Role role);

    Role selectRoleById(Integer id);

    //分页查询角色列表
    List<Role> selectRoleList(Integer page,Integer limit);

    //修改角色状态
    int updateRoleStatus(Integer id,Integer status);
}
