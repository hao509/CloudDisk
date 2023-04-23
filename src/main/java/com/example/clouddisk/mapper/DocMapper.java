package com.example.clouddisk.mapper;

import com.example.clouddisk.entity.Doc;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author ASUS
* @description 针对表【doc】的数据库操作Mapper
* @createDate 2023-04-04 17:00:04
* @Entity com.example.clouddisk.entity.Doc
*/
@Mapper
public interface DocMapper extends BaseMapper<Doc> {
    String getSumSize(String docowner);





}




