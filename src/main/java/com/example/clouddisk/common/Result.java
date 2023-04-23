package com.example.clouddisk.common;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author:faryhao
 * @create: 2023-04-04 17:02
 * @Description: 统一返回体
 */
@Data
@AllArgsConstructor
public class Result implements Serializable {

    /**
     * 状态码
     */
    private Integer code;
    /**
     * 返回信息
     */
    private String message;
    /**
     * 返回的数据
     */
    private Object data;

    /**
     * 私有化无参构造防止被构造
     */
    private Result(){

    }


    public static Result ok(){
        return new Result(200,"请求成功",null);
    }


    public static Result ok(Object data, String message) {
        return new Result(200, message, data);
    }


    public static Result ok(Object data) {
        return Result.ok(data, "请求成功");
    }


    public static Result error(Integer code, String message) {
        return new Result(code, message, null);
    }


    public static Result error(){
        return new Result(500,"请求失败",null);
    }

    public static Result error(String massage){
        return new Result(500,massage,null);
    }
}



