package com.oa.manager.system.action;

import com.oa.commons.base.BaseAction;
import com.oa.commons.model.DataGrid;
import com.oa.commons.model.PageParam;
import com.oa.manager.system.bean.SyDistrict;
import com.oa.manager.system.service.IDistrictService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * Created By WL on 2019/12/21
 */
@Controller
@RequestMapping("/district")
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

    /**
     * 分页条件查询 返回地区列表管理页面
     * @param district
     * @param pageParam
     * @return
     */
    @RequestMapping("query")
    public ModelAndView query(SyDistrict district, PageParam pageParam){
        DataGrid dataGrid = service.selectDistricts(district,pageParam);
        return ajaxJsonEscape(dataGrid);
    }

    /**
     * 跳转到添加页面
     * @return
     */
    @RequestMapping("addPage")
    public String addPage(){
        return "system/district/add";
    }

    /**
     * 添加地区
     * @param district
     * @param errors
     * @return
     */
    @RequestMapping("add")
    public ModelAndView add(@Valid SyDistrict district, Errors errors){
        if(errors.hasErrors()){
            ModelAndView mav=getValidationMessage(errors);
            if(mav!=null){
                return mav;
            }
        }
        String optRes = service.addDistrict(district);
        return ajaxDone(optRes);
    }

    /**
     * 跳转到修改地区页面
     * @param id
     * @param map
     * @return
     */
    @RequestMapping("updatePage")
    public String updatePage(String id,ModelMap map){
        SyDistrict district = service.get(SyDistrict.class, id);
        if(district==null){
            return NODATA;
        }
        map.addAttribute("d",district);
        return "system/district/update";
    }

    /**
     * 修改地区
     * @param district
     * @param errors
     * @return
     */
    public ModelAndView update(SyDistrict district,Errors errors){
        return null;
    }






}
