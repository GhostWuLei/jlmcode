package com.oa.manager.system.service;

import com.oa.commons.base.IBaseService;
import com.oa.commons.model.DataGrid;
import com.oa.commons.model.PageParam;
import com.oa.manager.system.bean.SyAction;
import com.oa.manager.system.bean.SyMenu;

import java.util.List;
import java.util.Map;

/**
 * Created By WL on 2019/12/20
 */
public interface IMenuService extends IBaseService{

    /**
     * 查询左侧菜单树
     * @return
     */
    List<Map<String,Object>> queryMenus();

    /**
     * 查询单个菜单 级联查询上级菜单名称
     * @param id
     * @return
     */
    Map findMenuById(String id);

    /**
     * 添加菜单
     * @param menu
     * @return
     */
    String addMenu(SyMenu menu);

    /**
     * 删除菜单
     * @param id
     * @return
     */
    String deleteMenu(String id);

    /**
     * 修改菜单
     * @param menu
     * @return
     */
    String updateMenu(SyMenu menu);

    /**
     * 查询所有操作 返回DataGrid至操作列表页面
     * @param action
     * @param pageParam
     * @return
     */
    DataGrid selectActions(SyAction action, PageParam pageParam);

    /**
     * 批量删除操作
     * @param ids
     * @return
     */
    boolean deleteActions(String[] ids);
}
