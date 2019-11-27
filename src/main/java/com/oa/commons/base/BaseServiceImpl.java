package com.oa.commons.base;

import com.oa.commons.model.IpInfo;
import com.oa.commons.model.Member;
import com.oa.commons.util.DateUtil;
import com.oa.commons.util.ServletUtil;
import com.oa.manager.system.bean.SyLog;
import com.oa.manager.system.bean.SyTableCustom;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.*;

/**
 * @author LiuJincheng
 * 
 * @desc	基本服务层基类 	所有的服务层都继承此基类
 *
 */

public abstract class BaseServiceImpl implements IBaseService {
	
	@Autowired
	protected IBaseDao dao;
	
	/**
	 * 添加	保存对象
	 * @param 	obj		需要添加的对象
	 * @return boolean	返回保存是否成功
	 */
	public boolean save(Object obj){
		return dao.save(obj);
	}
	/**
	 * 添加或更新
	 * 
	 * @param	obj		需要添加或更新的对象
	 * @return	boolean 添加成功或失败
	 */
	
	public boolean saveOrUpdate(Object o) {
		return dao.saveOrUpdate(o);
	}
	/**
	 * 批量添加或更新
	 * 
	 * @param	obj		需要添加或更新的对象集合
	 * @return	boolean 添加成功或失败
	 */
	public boolean saveOrUpdateAll(Collection<?> c) {
		return dao.saveOrUpdateAll(c);
	}
	/**
	 * 更新对象 根据对象id 
	 * 
	 * @param obj	对象
	 * @return boolean 	更新成功或失败
	 */
	public boolean update(Object o) {
		return dao.update(o);
	}
	/**
	 * 删除一个对象
	 * @param obj	对象
	 * @return	boolean	删除成功或失败
	 */
	public boolean delete(Object o){
		return	dao.delete(o);
	}
	/**
	 * 删除一个集合里面的全部对象
	 * 
	 * @param c 对象集合
	 * @return boolean 删除成功或失败
	 */
	public boolean deleteAll(Collection<?> c) {
		return dao.deleteAll(c);
	}
	/**
	 * 单个 查询	根据类型 id	 get方法
	 * @param <T>
	 * 
	 * @param 	c 	对象Class
	 * @param 	id 	对象ID
	 * @return	Object	返回数据对象
	 */
	public <T> T get(Class<T> c, Serializable id) {
		return dao.get(c, id);
	}
	
	
	/**
	 * 添加操作日志
	 * @param actionContent //操作内容
	 * @param actionDesc    //操作备注
	 * @return
	 */
	protected void  saveLog(String actionContent,String actionDesc){
		//获取session
		
		Member me= ServletUtil.getMember(); //获取当前登录者
		SyLog log=new SyLog();
		log.setActionContent(actionContent);
		log.setActionDesc(actionDesc);
		log.setActionTime(DateUtil.currentTimestamp());
		log.setUserId(me.getId());
		IpInfo ipInfo=me.getIpInfo();
		log.setActionIp(ipInfo.getIp());
		log.setActionIpInfo(ipInfo.getCountry()+" "+ipInfo.getRegion()+" "+ipInfo.getCity()+" "+ipInfo.getIsp());
		
		dao.save(log);
	}


	@SuppressWarnings("unchecked")
	public List<SyTableCustom> findTableCustomExport(short type){
		
		return dao.find("from SyTableCustom t where t.tbType=? and t.isExport=1 order by t.fieldSort asc",type);

	}
	@SuppressWarnings("unchecked")
	public List<SyTableCustom> findTableCustomPrint(short type){
		
		return dao.find("from SyTableCustom t where t.tbType=? and t.isPrint=1 order by t.fieldSort asc",type);

	}
	@SuppressWarnings("unchecked")
	public List<SyTableCustom> findTableCustomShow(short type){
		
		return dao.find("from SyTableCustom t where t.tbType=? and t.isShow=1 order by t.fieldSort asc",type);

	}
	
}
