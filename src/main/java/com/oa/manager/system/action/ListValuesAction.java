package com.oa.manager.system.action;

import com.oa.commons.base.BaseAction;
import com.oa.commons.model.DataGrid;
import com.oa.commons.model.PageParam;
import com.oa.manager.system.bean.ListValues;
import com.oa.manager.system.service.IListValuesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * 类名：ListValuesAction
 * 功能：系统管理--字典值管理
 * 详细：
 * Created By WL on 2019/12/20
 */
@Controller
@RequestMapping("list")
public class ListValuesAction extends BaseAction{

    @Autowired
    private IListValuesService service;

    /**
     * 跳转到字典值列表页面
     * @return
     */
    @RequestMapping("load")
    public String load(){
        return "system/list_values/load";
    }

    /**
     * 条件查询listValues值 并返回到列表页面
     * @param lv
     * @param pageParam
     * @return
     */
    @RequestMapping("query")
    public ModelAndView query(ListValues lv, PageParam pageParam){
        DataGrid dg = service.selectListValues(pageParam,lv);
        return ajaxJson(dg);
    }

    /**
     * 跳转到添加页面
     * @return
     */
    @RequestMapping("addPage")
    public String addPage(){
        return "system/list_values/add";
    }

    /**
     * 添加字典
     * @param lv
     * @param errors
     * @return
     */
    @RequestMapping("add")
    public ModelAndView add(@Valid ListValues lv, Errors errors){
        if(errors.hasErrors()){
            ModelAndView mav = getValidationMessage(errors);
            if(mav!=null){
                return mav;
            }
        }
        String optRes = service.addListValues(lv);
        return ajaxDone(optRes);
    }

    /**
     * 跳转到修改页面
     * @param id
     * @param map
     * @return
     */
    @RequestMapping("updatePage")
    public String updatePage(String id, ModelMap map){
        ListValues lv = service.get(ListValues.class, id);
        if(lv==null){
            return NODATA;
        }
        map.addAttribute("lv",lv);
        return "system/list_values/update";
    }

    /**
     * 修改字典值
     * @param lv
     * @param errors
     * @return
     */
    @RequestMapping("update")
    public ModelAndView update(@Valid ListValues lv,Errors errors){
        if(errors.hasErrors()){
            ModelAndView mav = getValidationMessage(errors);
            if(mav!=null){
                return mav;
            }
        }
        String optRes = service.updateListValues(lv);
        return ajaxDone(optRes);
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @RequestMapping("del")
    public ModelAndView delete(String[] ids){
        boolean temp = service.deleteListValues(ids);
        return ajaxDone(temp);
    }




}
