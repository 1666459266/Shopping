package com.shopping.core.service.serviceimpl;

import com.shopping.core.dao.UserMapper;
import com.shopping.core.entity.User;
import com.shopping.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired(required = false)
    private UserMapper userMapper;

    @Override
    public int insertUser(User user) {
        return userMapper.insertSelective(user);
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public User selectUserById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<User> selectUserList(Integer page,Integer limit) {
        return userMapper.selectUserList(page,limit);
    }

    @Override
    public User selectUserByUserName(String username) {
        return userMapper.selectUserByUserName(username);
    }

    @Override
    public int updateUserStatus(Integer id,Integer status) {
        return userMapper.updateUserStatus(id,status);
    }
}
