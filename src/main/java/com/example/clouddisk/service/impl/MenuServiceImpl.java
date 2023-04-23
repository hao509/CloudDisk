package com.example.clouddisk.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.clouddisk.common.R;
import com.example.clouddisk.dto.MenuDto;
import com.example.clouddisk.dto.MenuTree;
import com.example.clouddisk.entity.Menu;
import com.example.clouddisk.enums.BusinessCodeEnum;
import com.example.clouddisk.mapper.MenuMapper;

import com.example.clouddisk.mapper.RoleMapper;
import com.example.clouddisk.service.MenuService;
import com.example.clouddisk.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author zp
 * @Date 2023/3/12 1:46 PM
 * @Description: TODO
 */

@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    private MenuMapper mapper;
    private RoleMapper roleMapper;

    @Autowired
    public void setMapper(MenuMapper mapper) {
        this.mapper = mapper;
    }


    @Autowired
    public void setRoleMapper(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    /**
     * 根据菜单名称查询记录
     * @param menuName
     * @return
     */
    public Menu selectOne(String menuName){
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<Menu>();
        queryWrapper.eq("menu_name", menuName);
        return this.getOne(queryWrapper);
    }

    /**
     * 根据菜单Id查询记录
     * @param menuId
     * @return
     */
    public Menu selectOne(Integer menuId){
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<Menu>();
        queryWrapper.eq("menu_id", menuId);
        return this.getOne(queryWrapper);
    }

    /**
     * 添加菜单
     *
     * @param menu
     */
    @Override
    public R add(Menu menu) {


        //判断菜单是否存在
        Menu dbMenu=selectOne(menu.getMenuName());
        if (null!=dbMenu){
            return R.errorDataMap(BusinessCodeEnum.DATA_EXIST,"menu_name","该菜单已存在");
        }
        //如果该菜单不存在添加
//        try {
            mapper.insert(menu);
            return R.ok();
//        } catch (Exception e) {
//            return R.error(BusinessCodeEnum.OPERATION_FAILED);
//        }

    }

    /**
     * 更新菜单数据
     *
     * @param menu
     */
    @Override
    public R updateEntity(Menu menu) {

        //查询是否存在
        Menu dbMenu = selectOne(menu.getMenuId());
        if (null == dbMenu) {
            return R.errorDataMap(BusinessCodeEnum.DATA_NOT_EXIST,"menu_id","该菜单不存在");
        }
        //如果修改了菜单，就判断菜单名是否存在且不和本名进行对比
        if (null!=menu.getMenuName()&& !menu.getMenuName().equals(dbMenu.getMenuName()) &&null!=selectOne(menu.getMenuName())){
            return R.errorDataMap(BusinessCodeEnum.DATA_EXIST,"menu_name","该菜单名已存在");
        }
        //条件满足，开始修改
        try {
            this.updateById(menu);
            return R.ok();
        } catch (Exception e) {
            return R.error(BusinessCodeEnum.OPERATION_FAILED);
        }
    }

    /**
     * 根据Id删除菜单,menu_state改为0
     *
     * @param id
     */
    @Override
    public R deleteEntity(int id) {
        //判断菜单是否被存在
        if (mapper.totalByMenuId(id)>0){
            return R.error(2000,"该菜单角色正在被使用，不能删除");
        }
        //查询该菜单，是否作为别人的父节点
        QueryWrapper<Menu> wrapper = new QueryWrapper<>();
        wrapper.eq("menu_pid",id);
        wrapper.eq("menu_state",1);
        List<Menu> menuList = mapper.selectList(wrapper);
        if (menuList.size()>0){
            return R.error(2000,"该菜单作为其他菜单父节点，不能删除");
        }
        //查询是否存在该菜单
        Menu menu = selectOne(id);
        if (null==menu){
            return R.errorDataMap(BusinessCodeEnum.DATA_NOT_EXIST,"menu_id","该菜单不存在");
        }
        //查询该菜单是否被使用

        Menu menu1 = new Menu();
        menu1.setMenuId(id);
        menu1.setMenuState(0);
        this.updateById(menu1);
        return R.ok();
    }

    /**
     * 根据条件查询菜单集合
     *
     * @param params
     */
    @Override
    public R lists(Map<String, Object> params) {
        Page<MenuDto> page = (Page<MenuDto>) new Query<MenuDto>().getPage(params);
        MenuDto menuDto = new MenuDto();
        BeanMap beanMap = BeanMap.create(menuDto);
        beanMap.putAll(params);

        Page<MenuDto> menuDtoPage = mapper.selectListBySelective(page, menuDto);
        return R.ok().put("data",menuDtoPage);
    }

    /**
     * 根据父节点获取节点树
     *
     * @param pid
     * @return
     */
    @Override
    public R getMenuTreeByPid(Integer pid) {
        if (null == pid ||pid<0){
            pid =-1;
        }

        //判断是否存在该父节点
        if (null == selectOne(pid)) {
            return R.errorDataMap(BusinessCodeEnum.DATA_NOT_EXIST,"menu_pid","不存在该节点");
        }
        //存在就查询节点树
        List<MenuTree> menuTrees = mapper.selectMenuTreeByPid(pid);
        return R.ok().put("data",menuTrees);
    }

    /**
     * 角色授权，通过角色Id和菜单Id
     * @param roleId
     * @param menuIds
     * @return
     */
    @Override
    public R roleEmpowerByMenuIdAndRoleId(Integer roleId, List<Integer> menuIds) {
        //查询是否存在该角色
        if(null==roleMapper.selectById(roleId)){
            return R.errorDataMap(BusinessCodeEnum.DATA_NOT_EXIST,"role_id","该角色不存在");
        }
        //删除原先的权限
        mapper.deleteEmpowerByRoleId(roleId);
        //赋予新的权限
        mapper.insertEmpowerByRoleIdAndMenuIds(roleId, menuIds);
        return R.ok();
    }

    /**
     * 通过角色Id查询菜单
     *
     * @param roleId
     * @return
     */
    @Override
    public R selectMenuByRoleId(Integer roleId) {
        //查询该角色是否存在
        if (null == roleMapper.selectById(roleId)) {
            return R.errorDataMap(BusinessCodeEnum.DATA_NOT_EXIST,"roleId","角色不存在");
        }
        //查询
        List<Menu> menuList = mapper.selectByRoleId(roleId);
        return R.ok().put("data",menuList);
    }

    /**
     * 获取可用的菜单父节点
     * @return
     */
    @Override
    public R getMenuPidList() {
        QueryWrapper<Menu> wrapper = new QueryWrapper<>();
        wrapper.eq("menu_state",1);
        wrapper.eq("menu_type",0);
        try {
            List<Menu> menuPidList = mapper.selectList(wrapper);
            return R.ok().put("data",menuPidList);
        }catch (Exception e){
            return R.error(BusinessCodeEnum.OPERATION_FAILED);
        }
    }
}
