package com.example.clouddisk.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.clouddisk.entity.Role;
import com.example.clouddisk.service.RoleService;
import com.example.clouddisk.mapper.RoleMapper;
import org.springframework.stereotype.Service;

/**
* @author ASUS
* @description 针对表【role】的数据库操作Service实现
* @createDate 2023-04-04 17:00:36
*/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
    implements RoleService{

}




