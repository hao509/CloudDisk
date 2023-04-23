package com.example.clouddisk.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.clouddisk.entity.Admin;
import com.example.clouddisk.service.AdminService;
import com.example.clouddisk.mapper.AdminMapper;
import org.springframework.stereotype.Service;

/**
* @author ASUS
* @description 针对表【admin】的数据库操作Service实现
* @createDate 2023-04-04 16:59:54
*/
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin>
    implements AdminService{

}




