package com.oa.manager.system.action;

import com.oa.commons.base.BaseAction;
import com.oa.manager.system.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * 类名：MenuAction
 * 功能：系统管理--菜单管理
 * 详细：菜单的增删改查
 * Created By WL on 2019/12/20
 */
@Controller
@RequestMapping("menu")
public class MenuAction extends BaseAction{

    @Autowired
    private IMenuService service;

    /**
     * 跳转到菜单管理的主加载页面
     * @return
     */
    @RequestMapping("load")
    public String load(){
        return "system/menu/load";
    }

    /**
     * 查询左侧菜单树
     * @return
     */
    @RequestMapping("query")
    public ModelAndView query(){
        List<Map<String,Object>> list =  service.queryMenus();
        return ajaxJsonEscape(list);
    }

    /**
     * 跳转到菜单修改页面
     * @param id
     * @param map
     * @return
     */
    @RequestMapping("updatePage")
    public String updatePage(String id, ModelMap map){
        Map menuMap = service.findMenuById(id);
        if(menuMap.get("m")==null){
            return NODATA;
        }
        map.addAllAttributes(menuMap);
        return "system/menu/update";
    }



}
