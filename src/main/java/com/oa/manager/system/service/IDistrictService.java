package com.oa.manager.system.service;

import com.oa.commons.base.IBaseService;
import com.oa.commons.model.DataGrid;
import com.oa.commons.model.PageParam;
import com.oa.manager.system.bean.SyDistrict;

/**
 * Created By WL on 2019/12/21
 */
public interface IDistrictService extends IBaseService{

    /**
     * 分页条件查询 返回地区列表管理页面
     * @param district
     * @param pageParam
     * @return
     */
    DataGrid selectDistricts(SyDistrict district, PageParam pageParam);

    /**
     * 添加地区
     * @param district
     * @return
     */
    String addDistrict(SyDistrict district);

}
