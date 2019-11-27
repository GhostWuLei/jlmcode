package com.oa.manager.system.action;

import com.oa.commons.base.BaseAction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created By WL on 2019/11/26
 */
@Controller
@RequestMapping("/main")
public class MainAction extends BaseAction{


    @RequestMapping("")
    public String to(Model model){
        //model.addAttribute()
        model.addAttribute("name","admin");
        return "main/main";
    }

}
