/**  
 * @Project: jxoa
 * @Title: TrueNameTag.java
 * @Package com.oa.commons.tag
 * @date 2013-6-6 下午9:56:45
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
 * 类名：TrueNameTag
 * 功能：根据用户id，输出用户姓名
 * 详细：从缓存中获取
 * 作者：LiuJincheng
 * 版本：1.0
 * 日期：2013-6-6 下午9:56:45
 *
 */
public class TrueNameTag extends SimpleTagSupport{
	/**
	 * id
	 */
	private String id;
	
	@Override  
	 public void doTag() throws JspException, IOException {
		 PageContext ctx = (PageContext)getJspContext(); 
		 JspWriter out=ctx.getOut();
		
		 out.print(MyCache.getInstance().getTrueName(id));
			 
	 }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
