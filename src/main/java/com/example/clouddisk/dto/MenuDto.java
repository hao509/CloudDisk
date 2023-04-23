package com.example.clouddisk.dto;

import com.example.clouddisk.entity.Menu;
import lombok.Data;

/**
 * @Author zp
 * @Date 2023/3/13 3:45 PM
 * @Description: TODO
 */

@Data
public class MenuDto extends Menu {

    /**
     * 父节点名称
     */
    private String menuPidName;
}
