package com.oa.manager.system.service;

import com.oa.commons.base.IBaseService;
import com.oa.manager.system.bean.SyDept;

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

    /**
     * 修改部门信息
     * @param dept
     * @return
     */
    String updateDept(SyDept dept);

    /**
     * 添加部门
     * @param dept
     * @return
     */
    String addDept(SyDept dept);

    /**
     * 根据id删除部门
     * @param id
     * @return
     */
    String deleteDept(String id);
}
