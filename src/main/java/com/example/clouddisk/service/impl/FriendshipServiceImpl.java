package com.example.clouddisk.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.clouddisk.entity.Friendship;
import com.example.clouddisk.service.FriendshipService;
import com.example.clouddisk.mapper.FriendshipMapper;
import org.springframework.stereotype.Service;

/**
* @author ASUS
* @description 针对表【friendship】的数据库操作Service实现
* @createDate 2023-04-04 17:00:21
*/
@Service
public class FriendshipServiceImpl extends ServiceImpl<FriendshipMapper, Friendship>
    implements FriendshipService{

}




