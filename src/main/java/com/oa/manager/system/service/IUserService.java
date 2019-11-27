package com.oa.manager.system.service;

import com.oa.commons.base.IBaseService;

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
}
