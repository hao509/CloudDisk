package com.example.clouddisk.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.clouddisk.dto.MenuDto;
import com.example.clouddisk.dto.MenuTree;
import com.example.clouddisk.entity.Menu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author zp
 * @Date 2023/3/12 1:46 PM
 * @Description: TODO
 */

@Repository
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 通过父节点查询菜单树
     * @param pid
     * @return
     */
    List<MenuTree> selectMenuTreeByPid(Integer pid);

    /**
     * 通过角色Id，删除权限表
     * @param roleId
     * @return
     */
    Integer deleteEmpowerByRoleId(Integer roleId);

    /**
     * 批量授权
     * @param roleId
     * @param menuId
     * @return
     */
    Integer insertEmpowerByRoleIdAndMenuIds(@Param("roleId") Integer roleId, @Param("menuIdList") List<Integer> menuId);

    /**
     * 角色权限查询
     * @param roleId
     * @return
     */
    List<Menu> selectByRoleId(Integer roleId);

    /**
     * 统计某个菜单的使用
     * @param menuId
     * @return
     */
    Integer totalByMenuId(Integer menuId);

    Page<MenuDto> selectListBySelective(Page<MenuDto> page, @Param("menuDto") MenuDto menuDto);
}
