package com.oa.manager.system.service.impl;

import com.oa.commons.base.BaseServiceImpl;
import com.oa.commons.cache.MyCache;
import com.oa.commons.model.DataGrid;
import com.oa.commons.model.PageParam;
import com.oa.manager.system.bean.SyUsers;
import com.oa.manager.system.service.IUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created By WL on 2019/11/22
 */
@Service
public class UserServiceImpl extends BaseServiceImpl implements IUserService{

    /**
     * realm中 根据登录用户的id查询用户的权限
     * @param userId
     * @return
     */
    @Override
    public Map<String, Collection<String>> selectRolesPowers(String userId) {
        return null;
    }

    /**
     * 条件分页查询用户 用户展示界面
     * @param param
     * @param user
     * @return
     */
    @Override
    @SuppressWarnings("unchecked")
    public DataGrid selectUsers(PageParam param, SyUsers user) {
        DataGrid data = new DataGrid();
        StringBuffer sb = new StringBuffer("from SyUsers u where 1=1");
        List list = new ArrayList<>();
        if(StringUtils.isNotBlank(user.getUserName())){
            sb.append(" and u.userName like ? ");
            list.add("%"+user.getUserName()+"%");
        }
        if(StringUtils.isNotBlank(user.getTrueName())){
            sb.append(" and u.trueName like ? ");
            list.add("%"+user.getTrueName()+"%");
        }
        if (StringUtils.isNotBlank(user.getDeptId()) && !"0".equals(user.getDeptId())) {
            sb.append(" and u.deptId = ? ");
            list.add(user.getDeptId());
        }
        if(user.getUserSex()!=null){
            sb.append(" and u.userSex = ? ");
            list.add(user.getUserSex());
        }
        if(user.getUserStatus()!=null){
            sb.append(" and u.userStatus = ? ");
            list.add(user.getUserStatus());
        }
        //准备DataGrid数据
        Long total = (Long) dao.findUniqueOne("select count(*) " + sb.toString(), list);//deptId trueName userSex
        List<Map<String,Object>> rows = dao.findPage("select new Map(u.id as id,u.userName as userName,u.registerTime as registerTime,u.userStatus as userStatus," +
                                         "u.deptId as deptId,u.trueName as trueName,u.userSex as userSex)" + sb.toString(), param.getPage(), param.getRows(), list);
        for (Map<String, Object> map : rows) {
            map.put("deptName", MyCache.getInstance().getDeptName((String) map.get("deptId")));
        }

        data.setTotal(total);
        data.setRows(rows);

        return data;
    }


}
