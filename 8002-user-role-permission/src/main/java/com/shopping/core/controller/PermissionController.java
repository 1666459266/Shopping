package com.shopping.core.controller;

import com.shopping.core.entity.Permission;
import com.shopping.core.entity.User;
import com.shopping.core.service.PermissionService;
import com.shopping.core.service.UserService;
import com.shopping.core.util.Constant;
import com.shopping.core.util.CurrentUser;
import com.shopping.core.util.PageUtil;
import com.shopping.core.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController("permissionController")
public class PermissionController {
    @Autowired(required = false)
    private UserService userService;
    @Autowired(required = false)
    private PermissionService permissionService;

    @PostMapping("/permission")
    public ResponseUtil insertPermission(Permission permission){
        try {
            if (permission.getPermissionName() == null)
                return new ResponseUtil(Constant.FAIL,"权限名不能为空");
            if (permission.getPermissionDescription() == null)
                return new ResponseUtil(Constant.FAIL,"权限描述不能为空");
            if (permission.getIdentification() == null)
                return new ResponseUtil(Constant.FAIL,"权限标识不能为空");
            String username = CurrentUser.getUsername();
            User currentUser = userService.selectUserByUserName(username);
            permission.setStatus(1);
            permission.setCreateUserId(currentUser.getId());
            permission.setCreateUserName(currentUser.getUserName());
            permission.setCreateTime(new Date());
            int result = permissionService.insertPermission(permission);
            if (result > 0)
                return new ResponseUtil(Constant.SUCCESS,"添加成功");
            return new ResponseUtil(Constant.FAIL,"添加失败");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseUtil(Constant.FAIL,e.getMessage());
        }
    }

    @PutMapping("/permission")
    public ResponseUtil updatePermission(Permission permission){
        try {
            if (permission.getId() == null)
                return new ResponseUtil(Constant.FAIL,"ID不能为空");
            if (permission.getPermissionName() == null)
                return new ResponseUtil(Constant.FAIL,"权限名不能为空");
            if (permission.getPermissionDescription() == null)
                return new ResponseUtil(Constant.FAIL,"权限描述不能为空");
            if (permission.getIdentification() == null)
                return new ResponseUtil(Constant.FAIL,"权限标识不能为空");
            Permission existPermission = permissionService.selectPermissionById(permission.getId());
            if (existPermission == null)
                return new ResponseUtil(Constant.FAIL,"数据不存在");
             String username = CurrentUser.getUsername();
            User currentUser = userService.selectUserByUserName(username);
            existPermission.setPermissionName(permission.getPermissionName());
            existPermission.setPermissionDescription(permission.getPermissionDescription());
            existPermission.setIdentification(permission.getIdentification());
            existPermission.setUrl(permission.getUrl());
            existPermission.setSort(permission.getSort());
            existPermission.setParentid(permission.getParentid());
            existPermission.setUpdateUserId(currentUser.getId());
            existPermission.setUpdateUserName(currentUser.getUserName());
            existPermission.setUpdateTime(new Date());
            int result = permissionService.updatePermission(existPermission);
             if (result > 0)
                return new ResponseUtil(Constant.SUCCESS,"修改成功");
            return new ResponseUtil(Constant.FAIL,"修改失败");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseUtil(Constant.FAIL,e.getMessage());
        }
    }

    @DeleteMapping("/permission/{id}")
    public ResponseUtil deletePermission(@PathVariable("id") Integer id){
        try {
            if (id == null)
                return new ResponseUtil(Constant.FAIL,"ID不能为空");
            Permission existPermission = permissionService.selectPermissionById(id);
            if (existPermission == null)
                return new ResponseUtil(Constant.FAIL,"数据不存在");
            int result = permissionService.updatePermissionStatus(id,2);
            if (result > 0)
                return new ResponseUtil(Constant.SUCCESS,"删除成功");
            return new ResponseUtil(Constant.FAIL,"删除失败");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseUtil(Constant.FAIL,e.getMessage());
        }
    }

    @GetMapping("/permission/{id}")
    public ResponseUtil selectPermissionById(@PathVariable("id") Integer id){
        if (id == null)
            return new ResponseUtil(Constant.FAIL,"ID不能为空");
        Permission permission = permissionService.selectPermissionById(id);
        return new ResponseUtil(Constant.SUCCESS,permission);
    }

    @GetMapping("/permission/page/{page}/limit/{limit}")
    public ResponseUtil selectPermissionList(@PathVariable("page") Integer page,@PathVariable("limit") Integer limit){
        if (page == null || limit == null)
            return new ResponseUtil(Constant.FAIL,"分页信息不能为空");
        if (page <= 0 || limit <= 0)
            return new ResponseUtil(Constant.FAIL,"分页信息无效");
        PageUtil pageUtil = new PageUtil(page, limit);
        List<Permission> permissionList = permissionService.selectPermissionList(pageUtil.pageNum(page), pageUtil.limitNum(limit));
        return new ResponseUtil(Constant.SUCCESS,permissionList);
    }
}
