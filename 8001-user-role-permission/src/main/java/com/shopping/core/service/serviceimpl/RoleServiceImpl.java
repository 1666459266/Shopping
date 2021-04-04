package com.shopping.core.service.serviceimpl;

import com.shopping.core.dao.RoleMapper;
import com.shopping.core.entity.Role;
import com.shopping.core.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("roleService")
public class RoleServiceImpl implements RoleService {
    @Autowired(required = false)
    private RoleMapper roleMapper;

    @Override
    public int insertRole(Role role) {
        return roleMapper.insertSelective(role);
    }

    @Override
    public int updateRole(Role role) {
        return roleMapper.updateByPrimaryKeySelective(role);
    }

    @Override
    public Role selectRoleById(Integer id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Role> selectRoleList(Integer page,Integer limit) {
        return roleMapper.selectRoleList(page,limit);
    }

    @Override
    public int updateRoleStatus(Integer id,Integer status) {
        return roleMapper.updateRoleStatus(id,status);
    }
}
