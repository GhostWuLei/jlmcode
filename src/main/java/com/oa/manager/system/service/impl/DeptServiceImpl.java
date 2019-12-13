package com.oa.manager.system.service.impl;

import com.oa.commons.base.BaseServiceImpl;
import com.oa.commons.cache.MyCache;
import com.oa.commons.config.MsgConfig;
import com.oa.commons.model.DataGrid;
import com.oa.commons.model.PageParam;
import com.oa.manager.system.bean.SyDept;
import com.oa.manager.system.service.IDeptService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 部门管理 业务操作
 * Created By WL on 2019/12/2
 */
@Service
public class DeptServiceImpl extends BaseServiceImpl implements IDeptService{


    /**
     * 查询所有的部门，部分字段，返回list集合，集合里是Map键值对的方式{key1=value, key2=value}
     * @return
     */
    @Override
    public List<Map<String, Object>> selectAllDeptsMap() {
        String hql = "select new Map(id as id,deptName as deptName,superId as superId) from SyDept order by deptSort asc";
        List<Map<String, Object>> list = dao.find(hql);
        return list;
    }

    /**
     * 修改部门信息
     * @param dept
     * @return
     */
    @Override
    public String updateDept(SyDept dept) {
        //查看同级目录下是否有相同名称的部门
        Object obj = dao.findOne("from SyDept where deptName=? and superId=? and id!=?", dept.getDeptName(), dept.getSuperId(), dept.getId());
        if(obj==null){
            if(dao.update(dept)){
                //修改成功 保存日志
                saveLog("修改部门","部门名称："+dept.getDeptName());
                //更新缓存
                MyCache.getInstance().removeCache(MyCache.DEPTID2NAME,dept.getId());
                return MsgConfig.MSG_KEY_SUCCESS;//操作成功
            }else{
                return MsgConfig.MSG_KEY_FAIL;//操作失败
            }
        }else{
            return "msg.deptname.unique";//同级下有相同部门，请重新命名！
        }
    }

    /**
     * 添加部门
     * @param dept
     * @return
     */
    @Override
    public String addDept(SyDept dept) {
        //查看同级目录下是否有相同名称的部门
        Object obj = dao.findOne("from SyDept where deptName=? and superId=?", dept.getDeptName(), dept.getSuperId());
        if(obj==null){
            dao.save(dept);
            if(StringUtils.isNotBlank(dept.getId())){
                saveLog("添加部门","部门名称："+dept.getDeptName());
                return MsgConfig.MSG_KEY_SUCCESS;
            }else{
                return MsgConfig.MSG_KEY_FAIL;
            }
        }else{
            return "msg.deptname.unique";//同级下有相同部门，请重新命名！
        }
    }

    /**
     * 根据id删除部门 部门下面有部门或者用户时不能删除
     * @param id
     * @return
     */
    @Override
    public String deleteDept(String id) {
        Object obj = dao.findOne("from SyDept where superId = ?", id);
        if(obj!=null){
            return "msg.dept.haschild";//部门下属还有子部门，无法删除
        }else{
            Object one = dao.findOne("from SyUsers where deptId = ?", id);
            if(one!=null){
                return "msg.dept.hasuser";//有用户属于此部门，无法删除
            }
            SyDept dept = dao.get(SyDept.class, id);
            if(dept!=null){
                if(dao.delete(dept)){
                    saveLog("删除部门","部门名称："+dept.getDeptName());
                    //删除缓存
                    MyCache.getInstance().removeCache(MyCache.DEPTID2NAME,id);
                    return MsgConfig.MSG_KEY_SUCCESS;
                }else{
                    return MsgConfig.MSG_KEY_FAIL;
                }
            }else{
                return MsgConfig.MSG_KEY_NODATA;
            }
        }
    }

    /**
     * 查询部门 返回到选择部门界面
     * @param pageParam
     * @param dept
     * @return
     */
    @Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public DataGrid selectDepts(PageParam pageParam, SyDept dept) {
        DataGrid data = new DataGrid();
        StringBuffer sb = new StringBuffer("from SyDept d where 1=1 ");
        List list = new ArrayList();
        if(StringUtils.isNotBlank(dept.getDeptName())){
            sb.append(" and d.deptName like ?");
            list.add("%"+dept.getDeptName()+"%");
        }
        Long total = (Long) dao.findUniqueOne("select count(*) "+sb.toString(),list);
        //排序
        pageParam.appendOrderBy(sb);
        List<SyDept> rows = dao.findPage(sb.toString(), pageParam.getPage(), pageParam.getRows(), list);
        data.setTotal(total);
        data.setRows(rows);
        return data;
    }


}
