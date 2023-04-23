package com.example.clouddisk.controller;


import com.example.clouddisk.common.R;

import com.example.clouddisk.entity.Menu;
import com.example.clouddisk.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Author zp
 * @Date 2023/3/12 1:45 PM
 * @Description: TODO
 */

@RequestMapping("/menu")
@RestController
@CrossOrigin
public class MenuController {
    private MenuService menuService;

    @Autowired
    public void setMenuService(MenuService menuService) {

        this.menuService = menuService;
    }

    /**
     * 添加菜单
     * @param menu
     * @return
     */
//    @SaCheckPermission("menu/add")
    @PostMapping("/add")
    public R add(@Validated @RequestBody Menu menu){
        return menuService.add(menu);
    }

    /**
     * 更新菜单信息
     * @param menu
     * @return
     */
//    @SaCheckPermission("menu/update")
    @PostMapping("/update")
    public R update(@RequestBody Menu menu){
        return menuService.updateEntity(menu);
    }

    /**
     * 获取可用的菜单父节点
     * @return
     */
    @GetMapping("/getMenuPidList")
    public R getMenuPidList(){
        return menuService.getMenuPidList();
    }

    /**
     * 条件查询
     * @param params
     * @return
     */
//    @SaCheckPermission("menu/list")
    @PostMapping("/list")
    public R lists(@RequestBody Map<String,Object> params){
        return menuService.lists(params);
    }

    /**
     * 根据父节点获取子节点
     * @param menuPid
     * @return
     */
    @GetMapping("/tree/{menuPid}")
    public R tree(@PathVariable("menuPid") Integer menuPid){
        return menuService.getMenuTreeByPid(menuPid);
    }

    /**
     * 通过角色Id给某个角色授权
     * @param roleid
     * @param menuIds
     * @return
     */

    @PostMapping("/empower/{roleid}")
    public R empower(@PathVariable("roleid")Integer roleid, @RequestBody List<Integer> menuIds){

        return menuService.roleEmpowerByMenuIdAndRoleId(roleid, menuIds);
    }

    /**
     * 查询角色权限
     * @param roleId
     * @return
     */

    @GetMapping("/power/{roleId}")
    public R power(@PathVariable("roleId")Integer roleId){
        return menuService.selectMenuByRoleId(roleId);
    }

    /**
     * 删除菜单，menu_state改为0
     * @param menuId
     * @return
     */
//    @SaCheckPermission("menu/delete")
    @PostMapping("/delete/{menuId}")
    public R delete(@PathVariable("menuId")Integer menuId){
        return menuService.deleteEntity(menuId);
    }
}
