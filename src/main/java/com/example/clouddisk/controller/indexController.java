package com.example.clouddisk.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.example.clouddisk.common.Result;
import com.example.clouddisk.dto.UserRoleDto;
import com.example.clouddisk.entity.Role;
import com.example.clouddisk.entity.User;
import com.example.clouddisk.mapper.RoleMapper;
import com.example.clouddisk.mapper.UserMapper;
import com.example.clouddisk.service.RoleService;
import com.example.clouddisk.service.UserService;
import com.example.clouddisk.utils.SessionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author:faryhao
 * @create: 2023-04-02 15:09
 * @Description: indexController
 */
@RestController
@RequestMapping("/user")
@CrossOrigin
@Slf4j
public class indexController {
    @Autowired
    UserMapper userMapper;

   // @Autowired
   // RoleMapper roleMapper;



    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
//    @GetMapping("/selectbyid")
//    public User selectById(){
//        return userMapper.selectById(1);
//    }

    @ResponseBody
    @PostMapping("/login")
    public Result login(HttpServletRequest request, @RequestBody User user) {
//        获取用户名和密码
        UserRoleDto userRoleDto = new UserRoleDto();
        String id= user.getId();
        String password = user.getPassword();
        if(password==null){
            return Result.error("密码为空");
        }
        String pwd = DigestUtils.md5DigestAsHex(password.getBytes());

//        查询数据库
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id);
        User result = userService.getOne(wrapper);
        log.info(result.toString());

        QueryWrapper<Role> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("id", result.getId());
        Role role = roleService.getOne(wrapper1);

        if (result == null) {
            return Result.error("用户不存在");
        }
        if (!result.getPassword().equals(pwd)) {
            return Result.error("登录失败，密码错误");
        }
        else
            //(result.getPassword().equals(password))
        {
            userRoleDto.setId(result.getId());
            userRoleDto.setUserrole(role.getUserole());

            SessionUtil.setsession(userRoleDto.getId());
            String owner = SessionUtil.getsession();
            log.info(owner+"++");
            return Result.ok(userRoleDto);
        }
       // return null;
//        登录成功
    }
    @RequestMapping("/register")
    public Result register(HttpServletRequest request, @RequestBody User user){
        Role role = new Role();
        role.setId(user.getId());
        role.setUserole("user");
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
    /**
    *@Description:
    *@Param: 退出登录
    *@return:
    *@Author: faryhao
    *@date: 2023/4/23
    */
    @GetMapping("/logout")
    public Result logout(){
        SessionUtil.destroysession();
        return Result.ok("退出登录成功");
    }

}
