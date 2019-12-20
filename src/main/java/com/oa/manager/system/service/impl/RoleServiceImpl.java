package com.oa.manager.system.service.impl;

import com.oa.commons.base.BaseServiceImpl;
import com.oa.commons.cache.MyCache;
import com.oa.commons.config.MsgConfig;
import com.oa.commons.model.DataGrid;
import com.oa.commons.model.PageParam;
import com.oa.manager.system.bean.SyRole;
import com.oa.manager.system.service.IRoleService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created By WL on 2019/11/22
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl implements IRoleService{

    @Override
    public List<String> selectRolesByUserId(String userId) {
        return null;
    }

    /**
     * 查询所有角色 并返回到角色加载页面
     * @param pageParam
     * @param role
     * @return
     */
    @Override
    @SuppressWarnings("unchecked")
    public DataGrid selectRoles(PageParam pageParam, SyRole role) {
        DataGrid data = new DataGrid();
        StringBuffer sb = new StringBuffer("from SyRole r where 1=1");
        List list = new ArrayList<>();
        if (StringUtils.isNotBlank(role.getRoleName())) {
            sb.append(" and r.roleName like ?");
            list.add("%" + role.getRoleName() + "%");
        }
        if(StringUtils.isNotBlank(role.getRoleDesc())){
            sb.append(" and r.roleDesc like ?");
            list.add("%" + role.getRoleDesc() + "%");
        }
        Long total = (Long) dao.findUniqueOne("select count(*) "+sb.toString(),list);
        List<SyRole> rows = dao.findPage(sb.toString(), pageParam.getPage(), pageParam.getRows(), list);
        data.setTotal(total);
        data.setRows(rows);
        return data;
    }

    /**
     * 添加角色
     * @param role
     * @return
     */
    @Override
    public String addRole(SyRole role) {
        Object obj = dao.findOne("from SyRole where roleName=? ", role.getRoleName());
        if(obj==null){
            if(dao.save(role)){
                saveLog("添加角色","角色名称："+role.getRoleName());
                return MsgConfig.MSG_KEY_SUCCESS;
            }else{
                return MsgConfig.MSG_KEY_FAIL;
            }
        }else{
            return "msg.role.unique";//此角色名称已存在
        }
    }

    /**
     * 修改角色
     * @param role
     * @return
     */
    @Override
    public String updateRole(SyRole role) {
        Object obj = dao.findOne("from SyRole where id != ? and roleName = ?", role.getId(), role.getRoleName());
        if(obj==null){
            if(dao.update(role)){
                saveLog("修改角色","角色名称："+role.getRoleName());
                MyCache.getInstance().removeCache(MyCache.ROLE2NAME,role.getId());
                return MsgConfig.MSG_KEY_SUCCESS;
            }else{
                return MsgConfig.MSG_KEY_FAIL;
            }
        }else{
            return "msg.role.unique";
        }
    }

    /**
     * 批量删除角色
     * @param ids
     * @return
     */
    @Override
    public boolean deleteRoles(String[] ids) {
        List list = new ArrayList();
        for (String id : ids) {
            SyRole role = dao.get(SyRole.class, id);
            if(role!=null){
                saveLog("删除角色","角色名称："+role.getRoleName());
                list.add(role);
                MyCache.getInstance().removeCache(MyCache.ROLE2NAME,id);
            }
        }
        return dao.deleteAll(list);
    }


}
