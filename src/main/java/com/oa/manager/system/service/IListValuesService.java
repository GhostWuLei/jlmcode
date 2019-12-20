package com.oa.manager.system.service;

import com.oa.commons.base.IBaseService;
import com.oa.commons.model.DataGrid;
import com.oa.commons.model.PageParam;
import com.oa.manager.system.bean.ListValues;

import java.util.List;

/**
 * Created By WL on 2019/11/22
 */
public interface IListValuesService extends IBaseService{

    /**
     * 根据类型获取对应的数据字典集合 返回对应的list集合
     * @param type
     * @return
     */
    List<ListValues> selectListByType(Integer type);

    /**
     * 根据条件查询字典值
     * @param pageParam
     * @param lv
     * @return
     */
    DataGrid selectListValues(PageParam pageParam, ListValues lv);

    /**
     * 添加字典值
     * @param lv
     * @return
     */
    String addListValues(ListValues lv);

    /**
     * 修改字典值
     * @param lv
     * @return
     */
    String updateListValues(ListValues lv);

    /**
     * 批量删除字典值
     * @param ids
     * @return
     */
    boolean deleteListValues(String[] ids);
}
