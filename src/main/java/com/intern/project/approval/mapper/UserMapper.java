package com.intern.project.approval.mapper;

import com.intern.project.approval.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    User selectUser(String userId);
    void insertUser(User user);
    void deleteUser(String userId);
    void updateUser(User user);
}
