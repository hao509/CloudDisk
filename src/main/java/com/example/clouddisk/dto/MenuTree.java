package com.example.clouddisk.dto;

import com.example.clouddisk.entity.Menu;
import lombok.Data;

import java.util.List;

/**
 * @Author zp
 * @Date 2023/3/12 2:02 PM
 * @Description: TODO
 */
@Data
public class MenuTree extends Menu {

    /**
     * 子树
     */
    private List<Menu> children;
}
