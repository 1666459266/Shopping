package com.shopping.core.controller;

import com.shopping.core.entity.User;
import com.shopping.core.service.UserService;
import com.shopping.core.util.Constant;
import com.shopping.core.util.CurrentUser;
import com.shopping.core.util.PageUtil;
import com.shopping.core.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController("userController")
public class UserController {
    @Autowired(required = false)
    private UserService userService;

    @PostMapping("/user")
    public ResponseUtil insertUser(User user){
        try {
            if (user.getUserName() == null)
                return new ResponseUtil(Constant.FAIL,"用户名不能为空");
            if (user.getPassword() == null)
                return new ResponseUtil(Constant.FAIL,"密码不能为空");
            //判断用户名是否存在
            User existUser = userService.selectUserByUserName(user.getUserName());
            if (existUser != null)
                return new ResponseUtil(Constant.FAIL,"用户名已存在");
            //对密码进行加密
            String password = BCrypt.hashpw(user.getPassword(),BCrypt.gensalt());
            //获取当前用户
            String username = CurrentUser.getUsername();
            User currentUser = userService.selectUserByUserName(username);
            //将信息存入user对象
            user.setPassword(password);
            user.setStatus(1);
            user.setCreateUserId(currentUser.getId());
            user.setCreateUserName(currentUser.getUserName());
            user.setCreateTime(new Date());
            int result = userService.insertUser(user);
            if (result > 0)
                return new ResponseUtil(Constant.SUCCESS,"添加成功");
            return new ResponseUtil(Constant.FAIL,"添加失败");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseUtil(Constant.FAIL,e.getMessage());
        }
    }

    @PutMapping("/user")
    public ResponseUtil updateUser(User user){
        try {
            if (user.getId() == null)
                return new ResponseUtil(Constant.FAIL,"ID不能为空");
            User existUser = userService.selectUserById(user.getId());
            if (existUser == null)
                return new ResponseUtil(Constant.FAIL,"数据不存在");
            //获取当前用户
             String username = CurrentUser.getUsername();
            User currentUser = userService.selectUserByUserName(username);
            existUser.setGender(user.getGender());
            existUser.setNetName(user.getNetName());
            existUser.setSignature(user.getSignature());
            existUser.setTel(user.getTel());
            existUser.setUpdateUserId(currentUser.getId());
            existUser.setUpdateUserName(currentUser.getUserName());
            existUser.setUpdateTime(new Date());
            int result = userService.updateUser(existUser);
            if (result > 0)
                return new ResponseUtil(Constant.SUCCESS,"修改成功");
            return new ResponseUtil(Constant.FAIL,"修改失败");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseUtil(Constant.FAIL,e.getMessage());
        }
    }

    @DeleteMapping("/user/{id}")
    public ResponseUtil deleteUser(@PathVariable("id") Integer id){
        try {
            if (id == null)
                return new ResponseUtil(Constant.FAIL,"ID不能为空");
            User existUser = userService.selectUserById(id);
            if (existUser == null)
                return new ResponseUtil(Constant.FAIL,"数据不存在");
            int result = userService.updateUserStatus(id,2);
            if (result > 0)
                return new ResponseUtil(Constant.SUCCESS,"删除成功");
            return new ResponseUtil(Constant.FAIL,"删除失败");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseUtil(Constant.FAIL,e.getMessage());
        }
    }

    @GetMapping("/user/{id}")
    public ResponseUtil selectUserById(@PathVariable("id") Integer id){
        if (id == null)
            return new ResponseUtil(Constant.FAIL,"ID不能为空");
        User user = userService.selectUserById(id);
        return new ResponseUtil(Constant.SUCCESS,user);
    }

    @GetMapping("/user/page/{page}/limit/{limit}")
    public ResponseUtil selectUserList(@PathVariable("page") Integer page,@PathVariable("limit") Integer limit){
        if (page == null || limit == null)
            return new ResponseUtil(Constant.FAIL,"分页信息不能为空");
        if (page <= 0 || limit <= 0)
            return new ResponseUtil(Constant.FAIL,"分页信息无效");
        PageUtil pageUtil = new PageUtil(page, limit);
        List<User> userList = userService.selectUserList(pageUtil.pageNum(page),pageUtil.limitNum(limit));
        return new ResponseUtil(Constant.SUCCESS,userList);
    }

    @GetMapping("/user/username/{username}")
    public ResponseUtil selectUserByUserName(@PathVariable("username") String username){
        if (username == null)
            return new ResponseUtil(Constant.FAIL,"用户名不能为空");
        User user = userService.selectUserByUserName(username);
        return new ResponseUtil(Constant.SUCCESS,user);
    }
}
