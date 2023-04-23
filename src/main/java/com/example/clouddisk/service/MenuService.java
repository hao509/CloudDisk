package com.example.clouddisk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.clouddisk.common.R;
import com.example.clouddisk.entity.Menu;


import java.util.List;
import java.util.Map;

/**
 * @Author zp
 * @Date 2023/3/12 1:45 PM
 * @Description: TODO
 */

public interface MenuService extends IService<Menu> {
    /**添加菜单*/
    R add(Menu menu);

    /**更新菜单数据*/
    R updateEntity(Menu menu);

    /**删除菜单，menu_state改为0*/
    R deleteEntity(int id);

    /**根据条件查询菜单集合*/
    R lists(Map<String,Object> params);

    /**
     * 根据父节点获取节点树
     * @param pid
     * @return
     */
    R getMenuTreeByPid(Integer pid);

    /**
     * 角色授权，通过角色Id和菜单Id
     * @param roleId
     * @param menuId
     * @return
     */
    R roleEmpowerByMenuIdAndRoleId(Integer roleId, List<Integer> menuId);

    /**
     * 通过角色Id查询菜单
     * @param roleId
     * @return
     */
    R selectMenuByRoleId(Integer roleId);

    /**
     * 获取可用的菜单父节点
     * @return
     */
    R getMenuPidList();
}
