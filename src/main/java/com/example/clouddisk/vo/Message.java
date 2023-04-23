package com.example.clouddisk.vo;

import lombok.Data;

import java.util.List;

/**
 * @author:faryhao
 * @create: 2023-04-14 21:46
 * @Description: 1
 */
@Data
public class Message {
    //    时间
    private String time;
    //    接收方
    private String to;
    //    发送方
    private String from;
    //    消息
    private String msg;
    //    登录用户名
    private List<String> userNames;
}
