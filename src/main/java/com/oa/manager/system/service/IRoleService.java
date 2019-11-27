package com.oa.manager.system.service;

import com.oa.commons.base.IBaseService;

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
}
