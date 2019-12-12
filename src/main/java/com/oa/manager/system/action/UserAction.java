package com.oa.manager.system.action;

import com.oa.commons.base.BaseAction;
import com.oa.commons.config.MsgConfig;
import com.oa.commons.model.DataGrid;
import com.oa.commons.model.PageParam;
import com.oa.commons.model.RSAPublicKeyModel;
import com.oa.commons.util.RSAUtils;
import com.oa.commons.util.ServletUtil;
import com.oa.manager.system.bean.SyUsers;
import com.oa.manager.system.service.IDeptService;
import com.oa.manager.system.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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


    /**
     * 跳转到新增用户界面
     * @param map
     * @return
     */
    @RequestMapping("addPage")
    public String addPage(ModelMap map){
        String pwd = UUID.randomUUID().toString();
        ServletUtil.getSession().setAttribute("jmpw",pwd);
        RSAPublicKeyModel publicKey = RSAUtils.getPublicKeyModel(pwd);
        map.put("modulus",publicKey.getHexModulus());
        map.put("exponent",publicKey.getHexPublicExponent());
        return "system/organize/user/add";
    }

    /**
     * 添加用户
     * @param user
     * @param errors
     * @param roleIds
     * @return
     */
    @RequestMapping("add")
    public ModelAndView add(@Valid SyUsers user, Errors errors, String roleIds) throws UnsupportedEncodingException {
        if(errors.hasErrors()){
            ModelAndView mav = getValidationMessage(errors);
            if(mav!=null){
                return mav;
            }
        }
        //密码解密
        String pwd = RSAUtils.decryptStringByJs((String) ServletUtil.getSession().getAttribute("jmpw"),user.getUserPassword());
        user.setUserPassword(URLDecoder.decode(pwd,"utf-8"));
        String optRes = service.addUser(user);
        return ajaxDone(optRes);
    }

    /**
     * 跳转到修改用户信息界面
     * @param id
     * @param map
     * @return
     */
    @RequestMapping("updatePage")
    public String updatePage(String id,ModelMap map){
        SyUsers user = service.get(SyUsers.class,id);
        if(user==null){
            return NODATA;
        }
        user.setUserPassword("");
        map.addAttribute("u",user);
        return "system/organize/user/update";

    }

    /**
     * 修改用户
     * @param user
     * @param errors
     * @return
     */
    @RequestMapping("update")
    public ModelAndView update(@Valid SyUsers user,Errors errors){
        if(errors.hasErrors()){
            ModelAndView mav = getValidationMessage(errors,"userPassword");
            if(mav!=null){
                return mav;
            }
        }
        String optRes = service.updateUser(user);
        return ajaxDone(optRes);
    }

    /**
     * 批量删除用户
     * @param ids
     * @return
     */
    @RequestMapping("del")
    public ModelAndView del(String[] ids){
        return ajaxDone(service.deleteUsers(ids));
    }

    /**
     * 跳转到个人设置，用户修改密码
     * @param map
     * @return
     */
    @RequestMapping("updateMyPwPage")
    public String updateMyPwPage(ModelMap map){
        String pwd = UUID.randomUUID().toString();
        ServletUtil.getSession().setAttribute("jmpw",pwd);
        RSAPublicKeyModel publicKey = RSAUtils.getPublicKeyModel(pwd);
        map.put("modulus",publicKey.getHexModulus());
        map.put("exponent",publicKey.getHexPublicExponent());
        return "system/organize/user/user_set/update_password";
    }

    /**
     * 修改密码
     * @param oldPassword
     * @param userPassword
     * @param pwd
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("updateMyPw")
    public ModelAndView updateMyPw(String oldPassword,String userPassword,String pwd) throws UnsupportedEncodingException {
        if(!userPassword.equals(pwd)){
            List<String> errorMessage = new ArrayList<String>();
            errorMessage.add("两次输入的密码不一致！");
            ModelAndView mav = new ModelAndView("ajaxValidationMessage");
            mav.addObject(MsgConfig.STATUSCODE,MsgConfig.CODE_FAIL);
            mav.addObject("errorCount",1);
            mav.addObject(MsgConfig.MESSAGE,errorMessage);
            return mav;
        }
        //解密
        oldPassword = RSAUtils.decryptStringByJs((String) ServletUtil.getSession().getAttribute("jmpw"),oldPassword);
        oldPassword = URLDecoder.decode(oldPassword,"utf-8");
        userPassword=RSAUtils.decryptStringByJs((String)ServletUtil.getSession().getAttribute("jmpw"),userPassword);
        userPassword=URLDecoder.decode(userPassword, "utf-8");
        return ajaxDone(service.updateMyPassword(oldPassword,userPassword));
    }


    /**
     * 跳转到显示用户详情页面
     * @param id
     * @param map
     * @return
     */
    @RequestMapping("show")
    public String showInfo(String id,ModelMap map){
        SyUsers user = service.get(SyUsers.class, id);
        if(user==null){
            return NODATA;
        }
        map.addAttribute("u",user);
        return "system/organize/user/show";
    }

    /**
     * 跳转到重置用户密码界面
     * @param map
     * @return
     */
    @RequestMapping("updatePwPage")
    public String updatePwPage(ModelMap map){
        //生成加密秘钥
        String pwd = UUID.randomUUID().toString();
        ServletUtil.getSession().setAttribute("jmpw",pwd);
        RSAPublicKeyModel publicKey = RSAUtils.getPublicKeyModel(pwd);
        map.put("modulus",publicKey.getHexModulus());
        map.put("exponent",publicKey.getHexPublicExponent());
        return "system/organize/user/update_password";
    }

    /**
     * 重置用户密码
     * @param id
     * @param userPassword
     * @param pwd
     * @return
     */
    @RequestMapping("updatePw")
    public ModelAndView updatePassword(String id,String userPassword,String pwd) throws UnsupportedEncodingException {
        if(!userPassword.equals(pwd)){
            List<String> errorMessage = new ArrayList<String>();
            errorMessage.add("两次输入的密码不一致！");
            ModelAndView mav = new ModelAndView("ajaxValidationMessage");
            mav.addObject(MsgConfig.STATUSCODE,MsgConfig.CODE_FAIL);
            mav.addObject("errorCount",1);
            mav.addObject(MsgConfig.MESSAGE,errorMessage);
        }
        //解密
        userPassword = RSAUtils.decryptStringByJs((String) ServletUtil.getSession().getAttribute("jmpw"), userPassword);
        userPassword = URLDecoder.decode(userPassword, "utf-8");//111111
        return ajaxDone(service.updatePassword(id,userPassword));
    }

    /**
     * 跳转到选择用户页面
     * @return
     */
    @RequestMapping("lookUpPage")
    public String lookUpPage(){
        return "system/organize/user/lookup";
    }














}
