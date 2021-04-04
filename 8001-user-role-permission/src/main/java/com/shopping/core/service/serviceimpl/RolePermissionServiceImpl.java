package com.shopping.core.service.serviceimpl;

import com.shopping.core.dao.RolePermissionMapper;
import com.shopping.core.entity.RolePermission;
import com.shopping.core.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("rolePermissionService")
public class RolePermissionServiceImpl implements RolePermissionService {
    @Autowired(required = false)
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public int insertRolePermission(RolePermission rolePermission) {
        return rolePermissionMapper.insertSelective(rolePermission);
    }

    @Override
    public int updateRolePermission(RolePermission rolePermission) {
        return rolePermissionMapper.updateByPrimaryKeySelective(rolePermission);
    }

    @Override
    public int deleteRolePermissionById(Integer id) {
        return rolePermissionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public RolePermission selectRolePermissionById(Integer id) {
        return rolePermissionMapper.selectByPrimaryKey(id);
    }
}
