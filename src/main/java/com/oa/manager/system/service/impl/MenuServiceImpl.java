package com.oa.manager.system.service.impl;

import com.oa.commons.base.BaseServiceImpl;
import com.oa.commons.config.MsgConfig;
import com.oa.commons.model.DataGrid;
import com.oa.commons.model.PageParam;
import com.oa.commons.util.ServletUtil;
import com.oa.manager.system.bean.SyAction;
import com.oa.manager.system.bean.SyMenu;
import com.oa.manager.system.service.IMenuService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 菜单管理
 * Created By WL on 2019/12/20
 */
@Service
public class MenuServiceImpl extends BaseServiceImpl implements IMenuService{

    /**
     * 查询左侧菜单树
     * @return
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> queryMenus() {
        if(ServletUtil.isDeveloper()){
            List<Map<String,Object>> list = dao.find("select new Map(m.id as id,m.menuName as menuName,m.menuSuperId as menuSuperId,m.menuIcon as menuIcon,m.menuOpen as menuOpen) from SyMenu m order by menuSort asc");
            return list;
        }else{
            List<Map<String,Object>> list = dao.find("select new Map(m.id as id,m.menuName as menuName,m.menuSuperId as menuSuperId,m.menuIcon as menuIcon,m.menuOpen as menuOpen) from SyMenu m where menuStatus=1 order by menuSort asc");
            return list;
        }
    }

    /**
     * 查询单个菜单 级联查询上级菜单名称
     * @param id
     * @return
     */
    @Override
    public Map findMenuById(String id) {
        return (Map) dao.findOne("select new Map(m as m,(select menuName from SyMenu where id=m.menuSuperId) as superName) from SyMenu m where m.id=?",id);
    }

    /**
     * 添加菜单
     * @param menu
     * @return
     */
    @Override
    public String addMenu(SyMenu menu) {
        Object obj = dao.findOne("from SyMenu where menuSuperId = ? and menuName = ?", menu.getMenuSuperId(), menu.getMenuName());
        if(obj==null){
            dao.save(menu);
            if(StringUtils.isNotBlank(menu.getId())){
                saveLog("添加菜单","菜单名称："+menu.getMenuName());
                return MsgConfig.MSG_KEY_SUCCESS;
            }else{
                return MsgConfig.MSG_KEY_FAIL;
            }
        }else{
            return "msg.menuname.unique";//同级下有相同菜单，请重命名！
        }
    }

    /**
     * 删除菜单
     * @param id
     * @return
     */
    @Override
    public String deleteMenu(String id) {
        Object obj = dao.findOne("from SyMenu where menuSuperId = ?", id);
        if(obj!=null){
            return "msg.menu.haschild";//该菜单下还有子菜单，无法删除！
        }else{
            SyMenu menu = dao.get(SyMenu.class, id);
            if(menu!=null){
                if(dao.delete(menu)){
                    saveLog("删除菜单", "菜单名称："+menu.getMenuName());
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
     * 修改菜单
     * @param menu
     * @return
     */
    @Override
    public String updateMenu(SyMenu menu) {
        Object obj = dao.findOne("from SyMenu where id != ? and menuSuperId = ? and menuName = ?", menu.getId(), menu.getMenuSuperId(), menu.getMenuName());
        if(obj==null){
            if(ServletUtil.isDeveloper()){
                //开发人员可以修改所有字段
                if(dao.update(menu)){
                    saveLog("修改菜单", "菜单名:"+menu.getMenuName());
                    return MsgConfig.MSG_KEY_SUCCESS;
                }else{
                    return MsgConfig.MSG_KEY_FAIL;
                }

            }else{
                //非开发人员只能修改部分字段
                SyMenu old = dao.get(SyMenu.class, menu.getId());
                old.setMenuIcon(menu.getMenuIcon());
                old.setMenuName(menu.getMenuName());
                old.setMenuOpen(menu.getMenuOpen());
                old.setMenuSort(menu.getMenuSort());
                old.setMenuSuperId(menu.getMenuSuperId());
                saveLog("修改菜单", "菜单名:"+menu.getMenuName());
                return MsgConfig.MSG_KEY_SUCCESS;
            }
        }else{
            return "msg.menuname.unique";//菜单名已被占用
        }
    }

    /**
     * 查询所有操作 返回DataGrid至操作列表页面
     * @param action
     * @param pageParam
     * @return
     */
    @Override
    @SuppressWarnings("unchecked")
    public DataGrid selectActions(SyAction action, PageParam pageParam) {
        DataGrid dataGrid = new DataGrid();
        StringBuffer sb = new StringBuffer("from SyAction a where 1=1 ");
        List list = new ArrayList();
        if(StringUtils.isNotBlank(action.getMenuId())){
            sb.append(" and a.menuId=?");
            list.add(action.getMenuId());
        }
        if(StringUtils.isNotBlank(action.getActionName())){
            sb.append(" and a.actionName like ?");
            list.add("%"+action.getActionName()+"%");
        }
        if(StringUtils.isNotBlank(action.getActionUrl())){
            sb.append(" and a.actionUrl like ?");
            list.add("%"+action.getActionUrl()+"%");
        }
        Long total = (Long) dao.findUniqueOne("select count(*) "+sb.toString(),list);
        //排序规则
        if(StringUtils.isNotBlank(pageParam.getSort())){
            pageParam.appendOrderBy(sb);//排序
        }else{
            sb.append(" order by a.actionSort");
        }
        List<SyAction> rows = dao.findPage(sb.toString(),pageParam.getPage(),pageParam.getRows(),list);
        dataGrid.setTotal(total);
        dataGrid.setRows(rows);
        return dataGrid;
    }

    /**
     * 批量删除操作
     * @param ids
     * @return
     */
    @Override
    public boolean deleteActions(String[] ids) {
        for (String id : ids) {
            dao.delete("delete from SyAction where id=?",id);
        }
        return true;
    }


}
