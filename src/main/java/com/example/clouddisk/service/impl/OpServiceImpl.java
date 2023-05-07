package com.example.clouddisk.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.clouddisk.entity.Op;
import com.example.clouddisk.service.OpService;
import com.example.clouddisk.mapper.OpMapper;
import org.springframework.stereotype.Service;

/**
* @author ASUS
* @description 针对表【op】的数据库操作Service实现
* @createDate 2023-05-07 14:04:01
*/
@Service
public class OpServiceImpl extends ServiceImpl<OpMapper, Op>
    implements OpService{

}




