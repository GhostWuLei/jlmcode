package com.oa.manager.system.service.impl;

import com.oa.commons.base.BaseServiceImpl;
import com.oa.commons.cache.MyCache;
import com.oa.commons.config.MsgConfig;
import com.oa.commons.model.DataGrid;
import com.oa.commons.model.PageParam;
import com.oa.manager.system.bean.ListValues;
import com.oa.manager.system.service.IListValuesService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 字典值管理 业务层
 * Created By WL on 2019/11/22
 */
@Service
public class ListValuesServiceImpl extends BaseServiceImpl implements IListValuesService{


    @Override
    public List<ListValues> selectListByType(Integer type) {
        return null;
    }

    /**
     * 根据条件查询字典值
     * @param pageParam
     * @param lv
     * @return
     */
    @Override
    public DataGrid selectListValues(PageParam pageParam, ListValues lv) {
        DataGrid dataGrid = new DataGrid();
        StringBuffer sb = new StringBuffer("from ListValues lv where 1=1");
        List list = new ArrayList();
        if(lv.getListType()!=null){
            sb.append(" and lv.listType = ? ");
            list.add(lv.getListType());
        }
        if(StringUtils.isNotBlank(lv.getListValue())){
            sb.append(" and lv.listValue like ? ");
            list.add("%"+lv.getListValue()+"%");
        }
        Long total = (Long) dao.findUniqueOne("select count(*) "+sb.toString(),list);
        pageParam.appendOrderBy(sb);
        List<ListValues> rows = dao.findPage(sb.toString(),pageParam.getPage(),pageParam.getRows(),list);
        dataGrid.setTotal(total);
        dataGrid.setRows(rows);
        return dataGrid;
    }

    /**
     * 添加字典
     * @param lv
     * @return
     */
    @Override
    public String addListValues(ListValues lv) {
        Object obj = dao.findOne("from ListValues where listType = ? and listValue = ?", lv.getListType(), lv.getListValue());
        if(obj==null){
            dao.save(lv);
            if(StringUtils.isNotBlank(lv.getId())){
                saveLog("添加字典值", "类型:"+lv.getListType()+",字典值:"+lv.getListValue());
                //删除字典值
                MyCache.getInstance().removeCache(MyCache.TYPE2LISTVALUES,lv.getListType());
                return MsgConfig.MSG_KEY_SUCCESS;
            }else{
                return MsgConfig.MSG_KEY_FAIL;
            }
        }else{
            return "msg.listvalue.unique";//同目录下此字典值已存在
        }
    }

    /**
     * 修改字典值
     * @param lv
     * @return
     */
    @Override
    public String updateListValues(ListValues lv) {
        Object obj = dao.findOne("from ListValues where id != ? and listType = ? and listValue = ?", lv.getId(), lv.getListType(), lv.getListValue());
        if(obj==null){
            if(dao.update(lv)){
                saveLog("修改字典值"," 字典类型："+lv.getListType()+"，字典值："+lv.getListValue());
                MyCache.getInstance().removeCache(MyCache.LISTID2NAME,lv.getId());
                MyCache.getInstance().removeCache(MyCache.TYPE2LISTVALUES,lv.getListType());
                return MsgConfig.MSG_KEY_SUCCESS;
            }else{
                return MsgConfig.MSG_KEY_FAIL;
            }
        }else{
            return "msg.listvalue.unique";
        }
    }

    /**
     * 批量删除字典值
     * @param ids
     * @return
     */
    @Override
    public boolean deleteListValues(String[] ids) {
        //待删除的对象集合
        List<ListValues> list = new ArrayList<ListValues>();
        for (String id : ids) {
            ListValues lv = dao.get(ListValues.class, id);
            if(lv!=null){
                saveLog("删除字典值", "字典值:"+lv.getListValue());
                list.add(lv);
                MyCache.getInstance().removeCache(MyCache.LISTID2NAME,id);
                MyCache.getInstance().removeCache(MyCache.TYPE2LISTVALUES,lv.getListType());
            }
        }
        return deleteAll(list);
    }




}
