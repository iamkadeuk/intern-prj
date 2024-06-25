package com.intern.project.approval.service;

import com.intern.project.approval.domain.User;
import com.intern.project.approval.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private User user;

    @Autowired
    private UserMapper userMapper;

    public User getUser(String userId) {
        return userMapper.selectUser(userId);
    }

    public void putUser(User user) {
        userMapper.insertUser(user);
    }

    public void removeUser(String userId) {
        userMapper.deleteUser(userId);
    }

    public void modifyUser(User user) {
        userMapper.updateUser(user);
    }
}
