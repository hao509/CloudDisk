package com.example.clouddisk.mapper;

import com.example.clouddisk.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author ASUS
* @description 针对表【user】的数据库操作Mapper
* @createDate 2023-04-02 14:57:00
* @Entity com.example.clouddisk.entity.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {


}




