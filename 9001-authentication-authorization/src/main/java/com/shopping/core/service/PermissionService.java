package com.shopping.core.service;

import java.util.List;

public interface PermissionService {
    //根据用户名查询权限
    List<String> selectPermissionByUserName(String username);
}
