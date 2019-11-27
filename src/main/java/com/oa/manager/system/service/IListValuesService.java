package com.oa.manager.system.service;

import com.oa.commons.base.IBaseService;
import com.oa.manager.system.bean.ListValues;

import java.util.List;

/**
 * Created By WL on 2019/11/22
 */
public interface IListValuesService extends IBaseService{

    /**
     * 根据类型获取对应的数据字典集合 返回对应的list集合
     * @param type
     * @return
     */
    List<ListValues> selectListByType(Integer type);
}
