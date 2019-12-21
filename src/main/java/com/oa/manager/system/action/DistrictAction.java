package com.oa.manager.system.action;

import com.oa.commons.base.BaseAction;
import com.oa.manager.system.bean.SyDistrict;
import com.oa.manager.system.service.IDistrictService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created By WL on 2019/12/21
 */
@Controller
@RequestMapping("district")
public class DistrictAction extends BaseAction{

    @Autowired
    private IDistrictService service;

    /**
     * 跳转到地区列表页面
     * @param superId
     * @param map
     * @return
     */
    @RequestMapping("load")
    public String load(String superId,ModelMap map){
        if(StringUtils.isNotBlank(superId)){
            SyDistrict superDis = service.get(SyDistrict.class, superId);
            if(superDis!=null&&superDis.getSuperId()!=null){
                //表示上级不是顶级地区（地区管理）
                map.addAttribute("spName",superDis.getDisName());
                map.addAttribute("spId",superDis.getSuperId());
            }else{
                map.addAttribute("spName","地区管理");
            }
        }else{
           map.addAttribute("spName","地区管理");
        }
        return "system/district/load";
    }

}
