package com.oa.manager.system.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 类名：UserAction
 * 功能：系统管理--组织机构--用户管理
 * 详细：用户的增删改查
 * Created By WL on 2019/12/7
 */
@Controller
@RequestMapping("/user")
public class UserAction {



    /**
     * 跳转到用户加载页面
     * @return
     */
    @RequestMapping("load")
    public String load(){
        return "system/organize/user/load";
    }






}
