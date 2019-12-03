package com.oa.manager.system.service;

import com.oa.commons.base.IBaseService;

import java.util.List;
import java.util.Map;

/**
 * Created By WL on 2019/12/2
 */
public interface IDeptService extends IBaseService{


    /**
     * 查询所有的部门，部分字段，返回list集合，集合里是Map键值对的方式{key1=value, key2=value}
     * @return
     */
    List<Map<String,Object>> selectAllDeptsMap();
}
