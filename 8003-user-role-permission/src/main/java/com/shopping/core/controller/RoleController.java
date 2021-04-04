package com.shopping.core.controller;

import com.shopping.core.entity.Role;
import com.shopping.core.entity.User;
import com.shopping.core.service.RoleService;
import com.shopping.core.service.UserService;
import com.shopping.core.util.Constant;
import com.shopping.core.util.CurrentUser;
import com.shopping.core.util.PageUtil;
import com.shopping.core.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController("roleController")
public class RoleController {
    @Autowired(required = false)
    private UserService userService;
    @Autowired(required = false)
    private RoleService roleService;

    @PostMapping("/role")
    public ResponseUtil insertRole(Role role){
        try {
            if (role.getRoleName() == null)
                return new ResponseUtil(Constant.FAIL,"角色名不能为空");
            if (role.getRoleDescription() == null)
                return new ResponseUtil(Constant.FAIL,"角色描述不能为空");
            String username = CurrentUser.getUsername();
            User currentUser = userService.selectUserByUserName(username);
            role.setStatus(1);
            role.setCreateUserId(currentUser.getId());
            role.setCreateUserName(currentUser.getUserName());
            role.setCreateTime(new Date());
            int result = roleService.insertRole(role);
            if (result > 0)
                return new ResponseUtil(Constant.SUCCESS,"添加成功");
            return new ResponseUtil(Constant.FAIL,"添加失败");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseUtil(Constant.FAIL,e.getMessage());
        }
    }

    @PutMapping("/role")
    public ResponseUtil updateRole(Role role){
        try {
            if (role.getId() == null)
                return new ResponseUtil(Constant.FAIL,"ID不能为空");
            if (role.getRoleName() == null)
                return new ResponseUtil(Constant.FAIL,"角色名不能为空");
            if (role.getRoleDescription() == null)
                return new ResponseUtil(Constant.FAIL,"角色描述不能为空");
            Role existRole = roleService.selectRoleById(role.getId());
            if (existRole == null)
                return new ResponseUtil(Constant.FAIL,"数据不存在");
            String username = CurrentUser.getUsername();
            User currentUser = userService.selectUserByUserName(username);
            existRole.setRoleName(role.getRoleName());
            existRole.setRoleDescription(role.getRoleDescription());
            existRole.setUpdateUserId(currentUser.getId());
            existRole.setUpdateUserName(currentUser.getUserName());
            existRole.setUpdateTime(new Date());
            int result = roleService.updateRole(existRole);
            if (result > 0)
                return new ResponseUtil(Constant.SUCCESS,"修改成功");
            return new ResponseUtil(Constant.FAIL,"修改失败");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseUtil(Constant.FAIL,e.getMessage());
        }
    }

    @DeleteMapping("/role/{id}")
    public ResponseUtil deleteRole(@PathVariable("id") Integer id){
        try {
            if (id == null)
                return new ResponseUtil(Constant.FAIL,"ID不能为空");
            Role existRole = roleService.selectRoleById(id);
            if (existRole == null)
                return new ResponseUtil(Constant.FAIL,"数据不存在");
            int result = roleService.updateRoleStatus(id,2);
            if (result > 0)
                return new ResponseUtil(Constant.SUCCESS,"删除成功");
            return new ResponseUtil(Constant.FAIL,"删除失败");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseUtil(Constant.FAIL,e.getMessage());
        }
    }

    @GetMapping("/role/{id}")
    public ResponseUtil selectRoleById(@PathVariable("id") Integer id){
        if (id == null)
            return new ResponseUtil(Constant.FAIL,"ID不能为空");
        Role role = roleService.selectRoleById(id);
        return new ResponseUtil(Constant.SUCCESS,role);
    }

    @GetMapping("/role/page/{page}/limit/{limit}")
    public ResponseUtil selectRoleList(@PathVariable("page") Integer page,@PathVariable("limit") Integer limit){
        if (page == null || limit == null)
            return new ResponseUtil(Constant.FAIL,"分页信息不能为空");
        if (page <= 0 || limit <= 0)
            return new ResponseUtil(Constant.FAIL,"分页信息无效");
        PageUtil pageUtil = new PageUtil(page, limit);
        List<Role> roleList = roleService.selectRoleList(pageUtil.pageNum(page),pageUtil.limitNum(limit));
        return new ResponseUtil(Constant.SUCCESS,roleList);
    }
}
