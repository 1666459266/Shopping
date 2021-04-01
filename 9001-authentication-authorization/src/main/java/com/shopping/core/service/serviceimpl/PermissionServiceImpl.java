package com.shopping.core.service.serviceimpl;

import com.shopping.core.dao.PermissionMapper;
import com.shopping.core.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {
    @Autowired(required = false)
    private PermissionMapper permissionMapper;

    @Override
    public List<String> selectPermissionByUserName(String username) {
        return permissionMapper.selectPermissionByUserName(username);
    }
}
