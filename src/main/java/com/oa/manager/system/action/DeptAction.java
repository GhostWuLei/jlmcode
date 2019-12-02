package com.oa.manager.system.action;

import com.oa.manager.system.service.IDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 类名：DeptAction
 * 功能：系统管理--组织机构--部门管理
 * 详细：部门的增删改查
 * Created By WL on 2019/12/2
 */
@Controller
@RequestMapping("/dept")
public class DeptAction {

    @Autowired
    private IDeptService service;

    /**
     * 跳转到加载页面 查询出所有部门
     * @return
     */
    @RequestMapping("load")
    public String load(){
        return "system/organize/dept/load";
    }


    /**
     *
     * @return
     */
    @RequestMapping("load/all")
    public ModelAndView allDept(){
        return null;
    }

}
