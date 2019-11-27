package com.oa.manager.system.service;

import com.oa.commons.base.IBaseService;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created By WL on 2019/11/22
 */
public interface ILoginService extends IBaseService {


    /**
     * 用户登录验证
     * @param name
     * @param decode
     * @param request
     * @param response
     * @return
     */
    ModelAndView updateLogin(String name, String decode, HttpServletRequest request, HttpServletResponse response);


}
