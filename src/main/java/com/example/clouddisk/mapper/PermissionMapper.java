package com.example.clouddisk.mapper;

import com.example.clouddisk.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author ASUS
* @description 针对表【permission】的数据库操作Mapper
* @createDate 2023-04-04 17:00:31
* @Entity com.example.clouddisk.entity.Permission
*/
@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {

}




