package com.oa.manager.system.service.impl;

import com.oa.commons.base.BaseServiceImpl;
import com.oa.manager.system.service.IDeptService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 部门管理 业务操作
 * Created By WL on 2019/12/2
 */
@Service
public class DeptServiceImpl extends BaseServiceImpl implements IDeptService{


    @Override
    public List<Map<String, Object>> selectAllDeptsMap() {
        String hql = "select new Map(id as id,deptName as deptName,superId as superId) from SyDept order by deptSort asc";
        List<Map<String, Object>> list = dao.find(hql);
        return list;
    }


}
