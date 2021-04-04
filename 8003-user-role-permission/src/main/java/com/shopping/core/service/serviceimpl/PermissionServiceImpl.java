package com.shopping.core.service.serviceimpl;

import com.shopping.core.dao.PermissionMapper;
import com.shopping.core.entity.Permission;
import com.shopping.core.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {
    @Autowired(required = false)
    private PermissionMapper permissionMapper;

    @Override
    public int insertPermission(Permission permission) {
        return permissionMapper.insertSelective(permission);
    }

    @Override
    public int updatePermission(Permission permission) {
        return permissionMapper.updateByPrimaryKeySelective(permission);
    }

    @Override
    public Permission selectPermissionById(Integer id) {
        return permissionMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Permission> selectPermissionList(Integer page,Integer limit) {
        return permissionMapper.selectPermissionList(page,limit);
    }

    @Override
    public int updatePermissionStatus(Integer id,Integer status) {
        return permissionMapper.updatePermissionStatus(id,status);
    }
}
