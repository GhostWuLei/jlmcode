/**  
 * @Project: jxoa
 * @Title: LoginFilter.java
 * @Package com.oa.commons.filter
 * @date 2013-6-4 上午10:29:57
 * @Copyright: 2013 
 */
package com.oa.commons.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 
 * 类名：LoginFilter
 * 功能：登陆过滤
 * 详细：验证用户是否登录，用户访问权限
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-6-4 上午10:29:57
 *
 */
public class LoginFilter implements Filter{
	
	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest)arg0;
		HttpServletResponse response=(HttpServletResponse)arg1;
		String url=request.getRequestURI();//请求URL 包含工程名
		
		String accept=request.getHeader("Accept");
		System.out.println("请求："+url+"   ====accept:"+accept);
		
		chain.doFilter(request, response);
		return;
	}
	
	@Override
	public void destroy() {
		
		
	}
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
		
	}
	
	
}
