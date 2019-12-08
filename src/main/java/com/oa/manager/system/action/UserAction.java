package com.oa.manager.system.action;

import com.oa.commons.base.BaseAction;
import com.oa.commons.model.DataGrid;
import com.oa.commons.model.PageParam;
import com.oa.manager.system.bean.SyUsers;
import com.oa.manager.system.service.IDeptService;
import com.oa.manager.system.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 类名：UserAction
 * 功能：系统管理--组织机构--用户管理
 * 详细：用户的增删改查
 * Created By WL on 2019/12/7
 */
@Controller
@RequestMapping("/user")
public class UserAction extends BaseAction{

    @Autowired
    private IUserService service;
    @Autowired
    private IDeptService deptService;//部门

    /**
     * 跳转到用户加载页面
     * @return
     */
    @RequestMapping("load")
    public String load(){
        return "system/organize/user/load";
    }

    /**
     * 条件分页查询用户
     * @param param
     * @param user
     * @return
     */
    @RequestMapping("queryUsers")
    public ModelAndView queryUsers(PageParam param,SyUsers user){
        DataGrid dataGrid =  service.selectUsers(param,user);
        return ajaxJsonEscape(dataGrid);
    }










}
