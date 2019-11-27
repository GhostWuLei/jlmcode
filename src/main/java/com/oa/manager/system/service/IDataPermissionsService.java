package com.oa.manager.system.service;

import com.oa.commons.base.IBaseService;

/**
 * Created By WL on 2019/11/22
 */
public interface IDataPermissionsService extends IBaseService{


    /**
     * 根据 数据查询规则类型 获得对应的json格式查询规则
     * @param type
     * @return
     */
    String selectRules(String type);
}
