package com.example.clouddisk.controller;

import com.alibaba.excel.EasyExcelFactory;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.clouddisk.common.Result;
import com.example.clouddisk.entity.Docshare;
import com.example.clouddisk.entity.Op;
import com.example.clouddisk.entity.Role;
import com.example.clouddisk.entity.User;
import com.example.clouddisk.mapper.OpMapper;
import com.example.clouddisk.mapper.UserMapper;
import com.example.clouddisk.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

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

    @Autowired
    UserService userService;

    @Autowired
    OpMapper opMapper;

    @Autowired
    OpService opService;
    @Autowired
    RoleService roleService;
    @GetMapping("/userlist")
    public Result alluserlist(){
            return Result.ok(userMapper.getUserList());
    }

    @PostMapping("/deleteuser")
    public Result deleteuser(@RequestBody Map<String,String> map){
       // log.info(id+"user");
        userMapper.deleteById(map.get("id"));
        return Result.ok("OK");
    }
   @PostMapping("/status")
    public Result status(@RequestBody Map<String,String> map){
        String id = map.get("id");
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("id",id);
        User user = userService.getOne(wrapper);
        if(user.getIdstatus()==1){
            user.setIdstatus(0);
            userService.updateById(user);
        }else{
            user.setIdstatus(1);
            userService.updateById(user);
        }
        return Result.ok("OK");
   }
   @GetMapping("/log")
    public Result loglist(){
        QueryWrapper<Op> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("opid");
        return Result.ok(opMapper.selectList(wrapper));
   }

    @PostMapping("/getlog")
    public void getExcel(HttpServletResponse response) {
        try{
            String fileName = "log.xlsx";
            List<Op> data;
            fileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8.toString());
            response.setContentType("application/vnd.ms-excel");
            response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
           // QueryWrapper<Op> wrapper = new QueryWrapper<>();


            data = opService.list();
            EasyExcelFactory.write(response.getOutputStream(), Op.class)
                    .sheet("这是一张表名")
                    .doWrite(data);
            response.getOutputStream().flush();
        }catch (IOException e) {
            e.printStackTrace();
        }  finally {
            try {
                response.getOutputStream().close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @PostMapping("/register")
    public Result adminregister(HttpServletRequest request, @RequestBody User user){

            Role role = new Role();
            role.setId(user.getId());
            role.setUserole("admin");
            //默认账号能用
            user.setIdstatus(1);
            //默认500mb存储空间
            user.setUserspace("524,288,000");
            user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
            try {
                userService.save(user);
                roleService.save(role);
                return Result.ok("注册成功");
            } catch (Exception e) {
                return Result.error("已有该用户");
            }
        }



}
