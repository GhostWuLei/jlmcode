package com.oa.manager.system.action;

import com.oa.commons.base.BaseAction;
import com.oa.commons.model.PageParam;
import com.oa.manager.system.bean.SyDept;
import com.oa.manager.system.service.IDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * 类名：DeptAction
 * 功能：系统管理--组织机构--部门管理
 * 详细：部门的增删改查
 * Created By WL on 2019/12/2
 */
@Controller
@RequestMapping("/dept")
public class DeptAction extends BaseAction {

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
     * 加载左侧部门树
     * @return
     */
    @RequestMapping("load/all")
    public ModelAndView allDept(){
        return ajaxJsonEscape(service.selectAllDeptsMap());
    }

    /**
     * 部门修改页面
     * @param id
     * @param map
     * @return
     */
    @RequestMapping("updatePage")
    public String updatePage(String id, ModelMap map){
        SyDept dept = service.get(SyDept.class, id);
        if(dept==null){
            return NODATA;
        }
        map.addAttribute("dept",dept);
        return "system/organize/dept/update";
    }

    /**
     * 修改部门
     * @param dept
     * @param errors
     * @return
     */
    @RequestMapping("update")
    public ModelAndView update(@Valid SyDept dept, Errors errors){
        if(errors.hasErrors()){
            ModelAndView mav = getValidationMessage(errors);
            if(mav!=null){
                return mav;
            }
        }
        return ajaxDone(service.updateDept(dept));
    }

    /**
     * 跳转到添加页面
     * @return
     */
    @RequestMapping("addPage")
    public String addPage(){
        return "system/organize/dept/add";
    }


    /**
     * 添加部门
     * @param dept
     * @param errors
     * @return
     */
    @RequestMapping("add")
    public ModelAndView add(@Valid SyDept dept,Errors errors){
        //添加信息验证有误
        if(errors.hasErrors()){
            ModelAndView mav = getValidationMessage(errors);
            if(mav!=null){
                return mav;
            }
        }
        //信息无误 调用service添加部门
        return ajaxDone(service.addDept(dept));
    }

    /**
     * 根据id删除部门
     * @param id
     * @return
     */
    @RequestMapping("del")
    public ModelAndView delete(String id){
        String optRes = service.deleteDept(id);
        return ajaxDone(optRes);
    }

    /**
     * 跳转到选择部门界面
     * @return
     */
    @RequestMapping("lookUpPage")
    public String lookUpPage(){
        return "system/organize/dept/lookup";
    }

    /**
     * 查询部门 返回到选择部门界面
     * @param pageParam
     * @param dept
     * @return
     */
    @RequestMapping("lookUp")
    public ModelAndView lookUp(PageParam pageParam,SyDept dept){
        return ajaxJsonEscape(service.selectDepts(pageParam,dept));
    }






}
