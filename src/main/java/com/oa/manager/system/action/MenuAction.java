package com.oa.manager.system.action;

import com.oa.commons.base.BaseAction;
import com.oa.commons.config.MsgConfig;
import com.oa.commons.model.DataGrid;
import com.oa.commons.model.PageParam;
import com.oa.commons.util.ServletUtil;
import com.oa.manager.system.bean.SyAction;
import com.oa.manager.system.bean.SyMenu;
import com.oa.manager.system.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
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

    /**
     * 跳转至添加菜单页面
     * @return
     */
    @RequestMapping("addPage")
    public String addPage(){
        if(!ServletUtil.isDeveloper()){
            return NOPOWER;
        }
        return "system/menu/add";
    }

    /**
     * 添加菜单
     * @param menu
     * @param errors
     * @return
     */
    @RequestMapping("add")
    public ModelAndView add(@Valid SyMenu menu, Errors errors){
        if(!ServletUtil.isDeveloper()){
            return ajaxDoneError(MsgConfig.MSG_KEY_NOPOWER);
        }
        if(errors.hasErrors()){
            ModelAndView mav = getValidationMessage(errors);
            if(mav!=null){
                return mav;
            }
        }
        String optRes = service.addMenu(menu);
        return ajaxDone(optRes);
    }

    /**
     * 删除菜单
     * @param id
     * @return
     */
    @RequestMapping("del")
    public ModelAndView delete(String id){
        if(!ServletUtil.isDeveloper()){
            return ajaxDoneError(MsgConfig.MSG_KEY_NOPOWER);
        }
        String optRes = service.deleteMenu(id);
        return ajaxDone(optRes);
    }

    /**
     * 修改菜单
     * @param menu
     * @param errors
     * @return
     */
    @RequestMapping("update")
    public ModelAndView update(@Valid SyMenu menu,Errors errors){
        if(!ServletUtil.isDeveloper()){
            return ajaxDoneError(MsgConfig.MSG_KEY_NOPOWER);
        }
        if(errors.hasErrors()){
            ModelAndView mav = getValidationMessage(errors);
            if(mav!=null){
                return mav;
            }
        }
        String optRes = service.updateMenu(menu);
        return ajaxDone(optRes);
    }

    /**
     * 条件查询菜单操作 返回到操作列表
     * @param action
     * @param pageParam
     * @return
     */
    @RequestMapping("action/query")
    public ModelAndView queryActions(SyAction action, PageParam pageParam){
        if(!ServletUtil.isDeveloper()){
            return ajaxDoneError(MsgConfig.MSG_KEY_NOPOWER);
        }
        DataGrid dataGrid = service.selectActions(action,pageParam);
        return ajaxJsonEscape(dataGrid);
    }

    /**
     * 跳转到添加操作页面
     * @return
     */
    @RequestMapping("action/addPage")
    public String addActionPage(){
        if(!ServletUtil.isDeveloper()){
            return NOPOWER;
        }
        return "system/menu/action/add";
    }

    /**
     * 添加操作
     * @param action
     * @param errors
     * @return
     */
    @RequestMapping("action/add")
    public ModelAndView addAction(SyAction action,Errors errors){
        if(!ServletUtil.isDeveloper()){
            return ajaxDoneError(MsgConfig.MSG_KEY_NOPOWER);
        }
        if(errors.hasErrors()){
            ModelAndView mav = getValidationMessage(errors);
            if(mav!=null){
                return mav;
            }
        }
        return ajaxDone(service.save(action));
    }

    /**
     * 跳转到修改页面
     * @param id
     * @param map
     * @return
     */
    @RequestMapping("action/updatePage")
    public String updateActionPage(String id,ModelMap map){
        if(!ServletUtil.isDeveloper()){
            return NOPOWER;
        }
        SyAction action = service.get(SyAction.class, id);
        if(action==null){
            return NODATA;
        }
        map.addAttribute("act",action);
        return "system/menu/action/update";
    }

    /**
     * 修改操作
     * @param action
     * @param errors
     * @return
     */
    @RequestMapping("action/update")
    public ModelAndView updateAction(SyAction action,Errors errors){
        if(!ServletUtil.isDeveloper()){
            return ajaxDoneError(MsgConfig.MSG_KEY_NOPOWER);
        }
        if(errors.hasErrors()){
            ModelAndView mav = getValidationMessage(errors);
            if(mav!=null){
                return mav;
            }
        }
        return ajaxDone(service.update(action));
    }

    /**
     * 批量删除操作
     * @param ids
     * @return
     */
    @RequestMapping("action/del")
    public ModelAndView deleteActions(String[] ids){
        if(!ServletUtil.isDeveloper()){
            return ajaxDoneError(MsgConfig.MSG_KEY_NOPOWER);
        }
        boolean optRes = service.deleteActions(ids);
        return ajaxDone(optRes);
    }






}
