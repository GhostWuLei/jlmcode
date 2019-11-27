package com.oa.manager.system.action;

import com.oa.commons.base.BaseAction;
import com.oa.commons.config.MsgConfig;
import com.oa.commons.model.LoginInfo;
import com.oa.commons.model.Member;
import com.oa.commons.model.OnLineUser;
import com.oa.commons.model.RSAPublicKeyModel;
import com.oa.commons.util.RSAUtils;
import com.oa.commons.util.ServletUtil;
import com.oa.manager.system.service.ILoginService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 登录模块--用户登录
 * Created By WL on 2019/11/24
 */


@Controller
@RequestMapping("/sy_login")
public class LoginAction extends BaseAction{

    @Autowired
    private ILoginService service;


    /**
     * 跳转到登录页面
     * @param session
     * @return
     */

    @RequestMapping("")
    public String tologin(HttpSession session){
        Member me= ServletUtil.getMember();
        //判断当前用户是否已经登录
        if(me!=null){
            //获取当前登录的所有人员信息
            Map<String,OnLineUser> usersMap = ServletUtil.getOnLineUsers();
            OnLineUser om = usersMap.get(me.getId());
            if(om!=null){
                Map<String, LoginInfo> loginInfos = om.getLoginInfos();
                if(loginInfos.containsKey(session.getId())){
                    //存在用户的session 说明用户已经登录
                    return "has_login";
                }
            }
        }
        return "login";
    }


    /**
     * 生成加密的秘钥
     * @param session
     * @return
     */
    @RequestMapping("getEncryption")
    public ModelAndView getEncryption(HttpSession session){
        System.out.println("进入了getEncryption");
        Map<String, Object> map = new HashMap<String,Object>();
        //生成加密秘钥
        String pwd= UUID.randomUUID().toString();
        session.setAttribute("jmpw",pwd);
        RSAPublicKeyModel publicKey = RSAUtils.getPublicKeyModel(pwd);
        map.put(MsgConfig.STATUSCODE,MsgConfig.CODE_SUCCESS);
        map.put("modulus",publicKey.getHexModulus());
        map.put("exponent",publicKey.getHexPublicExponent());
        return ajaxJsonEscape(map);
    }

    /**
     * 用户登录
     * @param name
     * @param password
     * @param request
     * @param response
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("go")
    public ModelAndView login(String name, String password, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {

        String ps = (String) request.getSession().getAttribute("jmpw");
        if(StringUtils.isNotBlank(ps)){
            //进行解密操作
            String pwd = RSAUtils.decryptStringByJs(ps, password);
            return service.updateLogin(name, URLDecoder.decode(pwd,"utf-8"),request,response);
        }else{
            //加密信息获取失败 需要重新登录
            return ajaxDoneError("msg.login.nojmcode");
        }
    }

}
