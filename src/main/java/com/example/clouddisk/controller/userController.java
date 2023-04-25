package com.example.clouddisk.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.clouddisk.common.Result;
import com.example.clouddisk.entity.User;
import com.example.clouddisk.service.DocService;
import com.example.clouddisk.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.util.Map;
import java.util.Optional;

/**
 * @author:faryhao
 * @create: 2023-04-23 19:36
 * @Description: user
 */
@Slf4j
@RestController
@RequestMapping("/view")
public class userController {
    @Autowired
    UserService userService;
    @Autowired
    DocService docService;
   @PostMapping("/water")
    public Result water(@RequestBody Map<String,String> token){
       String usertoken = token.get("token");

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("id",usertoken);

        Optional<User> resultOptional = Optional.ofNullable(userService.getOne(wrapper));
        if (resultOptional.isPresent()) {
            User result = resultOptional.get();
            String stringWithoutCommas = result.getUserspace().replace(",", "");
            Double size = Double.parseDouble(stringWithoutCommas);
            Double sumSize = Double.parseDouble(docService.getSumSize(usertoken));
            log.info(String.valueOf(size));
            log.info(String.valueOf(sumSize));
           // DecimalFormat df = new DecimalFormat("#.##");
           // String per = df.format((size / sumSize)*100);
            double per = (sumSize/size)*100;
            log.info(String.valueOf(per));
            return Result.ok(per);
        } else {
            return Result.error("用户数据错误");
        }

    }

}
