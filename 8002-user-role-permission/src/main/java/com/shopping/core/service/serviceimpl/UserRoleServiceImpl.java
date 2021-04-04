package com.shopping.core.service.serviceimpl;

import com.shopping.core.dao.UserRoleMapper;
import com.shopping.core.entity.UserRole;
import com.shopping.core.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userRoleService")
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired(required = false)
    private UserRoleMapper userRoleMapper;

    @Override
    public int insertUserRole(UserRole userRole) {
        return userRoleMapper.insertSelective(userRole);
    }

    @Override
    public int updateUserRole(UserRole userRole) {
        return userRoleMapper.updateByPrimaryKeySelective(userRole);
    }

    @Override
    public int deleteUserRoleById(Integer id) {
        return userRoleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public UserRole selectUserRoleById(Integer id) {
        return userRoleMapper.selectByPrimaryKey(id);
    }
}
