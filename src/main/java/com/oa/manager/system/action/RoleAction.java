package com.oa.manager.system.action;

import com.oa.commons.base.BaseAction;
import com.oa.commons.model.DataGrid;
import com.oa.commons.model.PageParam;
import com.oa.manager.system.bean.SyRole;
import com.oa.manager.system.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * 类名：RoleAction
 * 功能：系统管理--角色管理
 * 详细：
 * Created By WL on 2019/12/13
 */
@Controller
@RequestMapping("/role")
public class RoleAction extends BaseAction{

    @Autowired
    private IRoleService service;

    /**
     * 跳转到角色加载页面
     * @return
     */
    @RequestMapping("load")
    public String load(){
        return "system/organize/role/load";
    }

    /**
     * 查询角色 并返回到角色列表页面
     * @param pageParam
     * @param role
     * @return
     */
    @RequestMapping("query")
    public ModelAndView query(PageParam pageParam, SyRole role){
        DataGrid dg = service.selectRoles(pageParam,role);
        return ajaxJsonEscape(dg);
    }

    /**
     * 跳转到添加角色界面
     * @return
     */
    @RequestMapping("addPage")
    public String addPage(){
        return "system/organize/role/add";
    }

    /**
     * 添加角色
     * @param role
     * @param errors
     * @return
     */
    @RequestMapping("add")
    public ModelAndView add(@Valid SyRole role, Errors errors){
        if(errors.hasErrors()){
            ModelAndView mav = getValidationMessage(errors);
            if(mav!=null){
                return mav;
            }
        }
        return  ajaxDone(service.addRole(role));
    }

    /**
     * 跳转到修改角色信息页面
     * @param id
     * @param map
     * @return
     */
    @RequestMapping("updatePage")
    public String updatePage(String id,ModelMap map){
        SyRole role = service.get(SyRole.class, id);
        if(role==null){
            return NODATA;
        }
        map.addAttribute("r",role);
        return "system/organize/role/update";
    }





}
