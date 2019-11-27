/**  
 * @Project: jxoa
 * @Title: UserDeptNameTag.java
 * @Package com.oa.commons.tag
 * @date 2013-8-5 下午2:54:26
 * @Copyright: 2013 
 */
package com.oa.commons.tag;

import com.oa.commons.cache.MyCache;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * 
 * 类名：UserDeptNameTag
 * 功能：根据用户id，输出部门名称
 * 详细：
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-8-5 下午2:54:26
 *
 */
public class UserDeptNameTag extends SimpleTagSupport{
	/**
	 * id
	 */
	private String id;
	
	@Override  
	 public void doTag() throws JspException, IOException {
		 PageContext ctx = (PageContext)getJspContext(); 
		 JspWriter out=ctx.getOut();
		 
		 out.print(MyCache.getInstance().getDeptNameByUserId(id));
			
	 }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
