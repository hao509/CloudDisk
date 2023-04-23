package com.example.clouddisk.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


/**
 * @Author zp
 * @Date 2023/3/12 1:41 PM
 * @Description: TODO
 */

@Data
@TableName("menu")
public class Menu {
    @TableId(value = "menu_id",type = IdType.AUTO)
    private Integer menuId;
    private String menuName;
    private String menuUrl;
    private Integer menuPid;
    private Integer menuType;
    private String menuClick;
    private String menuIcon;
    private Integer menuState;
}
