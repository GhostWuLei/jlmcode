package com.oa.manager.system.service;

import com.oa.commons.base.IBaseService;
import com.oa.commons.model.DataGrid;
import com.oa.commons.model.PageParam;
import com.oa.manager.system.bean.SyUsers;

import java.util.Collection;
import java.util.Map;

/**
 * Created By WL on 2019/11/22
 */
public interface IUserService extends IBaseService{


    /**
     * realm中 根据登录用户的id查询用户的权限
     * @param userId
     * @return
     */
    Map<String,Collection<String>> selectRolesPowers(String userId);

    /**
     * 条件分页查询用户 用户展示界面
     * @param param
     * @param user
     * @return
     */
    DataGrid selectUsers(PageParam param, SyUsers user);

    /**
     * 添加用户
     * @param user
     * @return
     */
    String addUser(SyUsers user);

    /**
     * 修改用户
     * @param user
     * @return
     */
    String updateUser(SyUsers user);

    /**
     * 修改密码
     * @param oldPassword
     * @param userPassword
     * @return
     */
    boolean updateMyPassword(String oldPassword, String userPassword);

    /**
     * 批量删除用户
     * @param ids
     * @return
     */
    boolean deleteUsers(String[] ids);

    /**
     * 重置用户密码
     * @param id
     * @param userPassword
     * @return
     */
    boolean updatePassword(String id, String userPassword);
}
