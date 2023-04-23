package com.example.clouddisk.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.clouddisk.entity.Doc;
import com.example.clouddisk.service.DocService;
import com.example.clouddisk.mapper.DocMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

/**
* @author ASUS
* @description 针对表【doc】的数据库操作Service实现
* @createDate 2023-04-04 17:00:04
*/
@Service
public class DocServiceImpl extends ServiceImpl<DocMapper, Doc>
    implements DocService{
    @Autowired
    DocMapper docMapper;
    @Override
    public String getSumSize(String docowner) {
        return docMapper.getSumSize(docowner);
    }



}




