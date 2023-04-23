package com.example.clouddisk.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.clouddisk.common.Result;
import com.example.clouddisk.entity.User;
import com.example.clouddisk.service.UserService;
import com.example.clouddisk.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.Wrapper;
import java.util.List;

/**
* @author ASUS
* @description 针对表【user】的数据库操作Service实现
* @createDate 2023-04-02 14:57:00
*/
@Service

public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{
    @Autowired
    UserMapper userMapper;
    public User getList(){

        return userMapper.selectById(1);

    }




}




