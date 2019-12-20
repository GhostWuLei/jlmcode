package com.oa.manager.system.service;

import com.oa.commons.base.IBaseService;

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
}
