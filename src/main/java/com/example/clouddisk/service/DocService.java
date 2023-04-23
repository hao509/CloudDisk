package com.example.clouddisk.service;

import com.example.clouddisk.entity.Doc;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author ASUS
* @description 针对表【doc】的数据库操作Service
* @createDate 2023-04-04 17:00:04
*/
public interface DocService extends IService<Doc> {
    String getSumSize(String docowner);


}
