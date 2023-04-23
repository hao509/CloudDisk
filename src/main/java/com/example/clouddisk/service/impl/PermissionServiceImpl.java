package com.example.clouddisk.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.clouddisk.entity.Permission;
import com.example.clouddisk.service.PermissionService;
import com.example.clouddisk.mapper.PermissionMapper;
import org.springframework.stereotype.Service;

/**
* @author ASUS
* @description 针对表【permission】的数据库操作Service实现
* @createDate 2023-04-04 17:00:31
*/
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission>
    implements PermissionService{

}




