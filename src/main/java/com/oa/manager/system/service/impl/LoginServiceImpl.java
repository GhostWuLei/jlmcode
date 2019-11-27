package com.oa.manager.system.service.impl;

import com.oa.commons.base.BaseServiceImpl;
import com.oa.commons.config.BaseConfig;
import com.oa.commons.config.MsgConfig;
import com.oa.commons.config.WebConfig;
import com.oa.commons.model.IpInfo;
import com.oa.commons.model.LoginInfo;
import com.oa.commons.model.Member;
import com.oa.commons.model.OnLineUser;
import com.oa.commons.util.*;
import com.oa.manager.system.bean.SyLoginLog;
import com.oa.manager.system.bean.SyUsers;
import com.oa.manager.system.service.ILoginService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created By WL on 2019/11/22
 */
@Service
public class LoginServiceImpl extends BaseServiceImpl implements ILoginService{

    /**
     * 用户登录验证
     * @param name
     * @param password
     * @param request
     * @param response
     * @return
     */
    @Override
    public ModelAndView updateLogin(String name, String password, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("ajaxMessage");
        HttpSession session = request.getSession();
        //获取登录的ip地址
        String loginIp = IpUtil.getIpAddr(request);
        String devName = BaseConfig.getInstance().getDevName();
        String saName = BaseConfig.getInstance().getSaName();
        //1.验证系统防火墙，例：ip,时间等访问限制，首先排除开发人员和超级管理员
        if(!name.equals(BaseConfig.getInstance().getDevName())||!name.equals(BaseConfig.getInstance().getSaName())) {
            WebConfig wc = BaseConfig.webconfig;
            //先判断是否禁止登录系统 1表示开启 0表示关闭
            if (wc.getAppStart() != 1) {
                //表示系统禁止访问
                mav.setViewName("ajaxDone");
                mav.addObject(MsgConfig.STATUSCODE, MsgConfig.CODE_FAIL);
                mav.addObject(MsgConfig.MESSAGE, "系统已设置禁止访问！请联系管理员！");
                return mav;
            }
            //判断是否在可以登录的时间范围之内
            if (!AppUtil.checkLoginTime(new Date(), wc.getLoginStartTime(), wc.getLoginEndTime())) {
                //表示不再允许登录的时间范围内
                mav.setViewName("ajaxDone");
                mav.addObject(MsgConfig.STATUSCODE, MsgConfig.CODE_FAIL);
                mav.addObject(MsgConfig.MESSAGE, "系统只能在" + wc.getLoginStartTime() + " 至 " + wc.getLoginEndTime() + "之间才能访问！");
                return mav;
            }
            //进行ip登录验证
            //进行ip验证
            if (StringUtils.isNotBlank(wc.getAllowIps()) && !AppUtil.checkIp(wc.getAllowIps(), loginIp)) {
                mav.setViewName("ajaxDone");
                mav.addObject(MsgConfig.STATUSCODE, MsgConfig.CODE_FAIL);
                mav.addObject(MsgConfig.MESSAGE, "系统已设置ip限制！");
                return mav;
            } else {
                if (StringUtils.isNotBlank(wc.getLimitIps()) && AppUtil.checkIp(wc.getLimitIps(), loginIp)) {
                    mav.setViewName("ajaxDone");
                    mav.addObject(MsgConfig.STATUSCODE, MsgConfig.CODE_FAIL);
                    mav.addObject(MsgConfig.MESSAGE, "系统已设置ip限制！");
                    return mav;
                }
            }
        }

        //2.根据用户名 从数据库中查询是否存在此用户
        SyUsers u = (SyUsers) dao.findOne("from SyUsers where userName = ?",name);
        if(u == null){
            System.out.println("不存在此用户！");
            mav.addObject(MsgConfig.STATUSCODE,MsgConfig.CODE_FAIL);
            mav.addObject(MsgConfig.MESSAGE,"msg.login.failure");
            return mav;
        }
        //3.验证此用户是否被限制登录 1 表示可用 0 表示不可用
        if(u.getUserStatus()==(short)0){
            System.out.println("用户的登录状态被限制！");
            mav.addObject(MsgConfig.STATUSCODE,MsgConfig.CODE_FAIL);
            mav.addObject(MsgConfig.MESSAGE,"msg.login.restrict");
            return mav;
        }

        //根据登录ip获取ip地址信息
        IpInfo ipInfo = IpUtil.getIpInfo(loginIp);
        Timestamp loginTime = DateUtil.currentTimestamp();
        //密码错误的最大次数
        int num = BaseConfig.webconfig.getPwdErrorNum();
        //密码输入达到上限后被限制的冷却时间 分钟
        int time = BaseConfig.webconfig.getPwdErrorTime();
        //4.验证用户是否因为密码输入错误次数过多，处于限制登录状态，判断用户密码输入错误的次数是否达到指定次数
        if(u.getErrorCount()>=num){
            //当用户输入密码错误的次数大于系统配置的错误次数，获取错误时间，进行判断
            long gotime = loginTime.getTime()-u.getErrorTime().getTime();
            if(gotime<time*60000){
                //如果冷却时间未到
                mav.addObject(MsgConfig.STATUSCODE,MsgConfig.CODE_FAIL);
                mav.addObject(MsgConfig.MESSAGE,"msg.login.restricttime");
                mav.addObject(MsgConfig.MESSAGEVALUES,num+","+time+"分钟");
                return mav;
            }else{
                //如果冷却时间已到
                u.setErrorCount((short) 0);
            }
        }
        //5.登录验证 验证密码
        if(MD5Util.MD5Validate(password,u.getUserPassword())){
            Subject currentUser = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(u.getId(),password);
            currentUser.login(token);//登录认证 记录登录信息
            System.out.println("********************登录成功*****************");
            //5.1 登录成功 保存一些用户信息到session中
            Member me = new Member();
            me.setId(u.getId());
            me.setDeptId(u.getDeptId());
            me.setIpInfo(ipInfo);
            me.setLoginTime(loginTime);
            session.setAttribute("minfo",me);

            //将当前用户是否是超级管理员或者开发人员放入session中
            if(name.equals(BaseConfig.getInstance().getDevName())){
                session.setAttribute("dev",true);
            }else{
                session.setAttribute("dev",false);
            }
            if(name.equals(BaseConfig.getInstance().getSaName())){
                session.setAttribute("sa",true);
            }else{
                session.setAttribute("sa",false);
            }

            //5.2 保持一些用户登陆信息 到全局用户列表中
            // 获取全局用户列表 将此次登录的用户保存到全局用户列表当中
            Map<String, OnLineUser> usersMap = ServletUtil.getOnLineUsers();
            OnLineUser onmy = usersMap.get(u.getId());
            if(onmy==null){
                onmy = new OnLineUser();
            }
            onmy.setId(u.getId());
            onmy.setTrueName(u.getTrueName());
            onmy.setDeptId(u.getDeptId());
            onmy.setSex(u.getUserSex());
            Map<String,LoginInfo> loginInfos = onmy.getLoginInfos();
            if(loginInfos==null){
                loginInfos = new HashMap<String,LoginInfo>();
            }
            LoginInfo loginInfo = new LoginInfo();
            loginInfo.setId(FileUtils.getUUID());
            loginInfo.setIpInfo(ipInfo);
            loginInfo.setLoginTime(loginTime);
            loginInfo.setLoginType(1);//登录类型：1 网页 2 安卓客户端
            loginInfos.put(session.getId(),loginInfo);
            onmy.setLoginInfos(loginInfos);
            usersMap.put(u.getId(),onmy);
            session.setAttribute("loginType",1);//标记此session登陆方式 用于退出时使用

            //5.3记录登录日志
            SyLoginLog log = new SyLoginLog();
            log.setUserId(u.getId());
            log.setLoginType((short) 1);
            log.setLoginDesc("登录成功");
            log.setLoginIp(loginIp);
            log.setIpInfoCountry(ipInfo.getCountry());
            log.setIpInfoRegion(ipInfo.getRegion());
            log.setIpInfoCity(ipInfo.getCity());
            log.setIpInfoIsp(ipInfo.getIsp());
            log.setLoginTime(loginTime);
            dao.save(log);//保存登录日志

            //5.4 更新用户最近的登录时间还有ip
            u.setLastLoginIp(loginIp);
            u.setLastLoginTime(loginTime);
            u.setErrorCount((short) 0);
            dao.update(u);//更新用户

            //返回
            mav.addObject(MsgConfig.STATUSCODE,MsgConfig.CODE_SUCCESS);
            mav.addObject(MsgConfig.MESSAGE,"msg.login.success");

            session.removeAttribute("jmpw");//清除加密密码
            session.setAttribute("fromLogin",true);//标记刚刚执行的登录操作
            return mav;
        }else{
            //认证失败
            System.out.println("密码错误");
            //登录日志
            SyLoginLog log = new SyLoginLog();
            log.setUserId(u.getId());
            log.setLoginType((short) 1);
            log.setLoginDesc("登录失败");
            log.setLoginIp(loginIp);
            log.setIpInfoCountry(ipInfo.getCountry());
            log.setIpInfoRegion(ipInfo.getRegion());
            log.setIpInfoCity(ipInfo.getCity());
            log.setIpInfoIsp(ipInfo.getIsp());
            log.setLoginTime(loginTime);

            dao.save(log);//保存登录失败的日志
            u.setErrorCount((short) (u.getErrorCount()+1));
            dao.update(u);
            mav.addObject(MsgConfig.STATUSCODE,MsgConfig.CODE_FAIL);
            mav.addObject(MsgConfig.MESSAGE,"msg.login.failure");//用户名或密码错误 请从新登录
            return mav;
        }

    }


}
