package com.example.clouddisk.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.clouddisk.common.Result;
import com.example.clouddisk.entity.Docshare;
import com.example.clouddisk.entity.User;
import com.example.clouddisk.mapper.UserMapper;
import com.example.clouddisk.service.AdminService;
import com.example.clouddisk.service.DocService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author:faryhao
 * @create: 2023-05-07 14:58
 * @Description: 管理员操作
 */
@RestController
@RequestMapping("/admin")
@CrossOrigin
@Slf4j
public class adminController {
    @Autowired
    AdminService adminService;

    @Autowired
    DocService docService;

    @Autowired
    UserMapper userMapper;

    @GetMapping("/userlist")
    public Result alluserlist(){
            return Result.ok(userMapper.getUserList());
    }

}
