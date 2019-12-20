package com.oa.manager.system.service.impl;

import com.oa.commons.base.BaseServiceImpl;
import com.oa.commons.util.ServletUtil;
import com.oa.manager.system.service.IMenuService;
import org.springframework.stereotype.Service;

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


}
