package com.oa.manager.system.service;

import com.oa.commons.base.IBaseService;
import com.oa.commons.model.DataGrid;
import com.oa.commons.model.PageParam;
import com.oa.manager.system.bean.SyRole;

import java.util.List;

/**
 * Created By WL on 2019/11/22
 */
public interface IRoleService extends IBaseService{


    /**
     * 根据用户id获取当前用户拥有的角色ids 返回list集合
     * @param userId
     * @return
     */
    List<String> selectRolesByUserId(String userId);

    /**
     * 查询所有角色 并返回到角色加载页面
     * @param pageParam
     * @param role
     * @return
     */
    DataGrid selectRoles(PageParam pageParam, SyRole role);

    /**
     * 添加角色
     * @param role
     * @return
     */
    String addRole(SyRole role);

    /**
     * 修改角色
     * @param role
     * @return
     */
    String updateRole(SyRole role);

    /**
     * 批量删除角色
     * @param ids
     * @return
     */
    boolean deleteRoles(String[] ids);
}
