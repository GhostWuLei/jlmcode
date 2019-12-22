package com.oa.manager.system.service.impl;

import com.oa.commons.base.BaseServiceImpl;
import com.oa.commons.config.MsgConfig;
import com.oa.commons.model.DataGrid;
import com.oa.commons.model.PageParam;
import com.oa.manager.system.bean.SyDistrict;
import com.oa.manager.system.service.IDistrictService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created By WL on 2019/12/21
 */
@Service
public class DistrictServiceImpl extends BaseServiceImpl implements IDistrictService{

    /**
     * 分页条件查询 返回地区列表管理页面
     * @param district
     * @param pageParam
     * @return
     */
    @Override
    @SuppressWarnings("unchecked")
    public DataGrid selectDistricts(SyDistrict district, PageParam pageParam) {
        DataGrid dataGrid = new DataGrid();
        List list = new ArrayList();
        StringBuffer sb = new StringBuffer("from SyDistrict d where 1=1");
        sb.append(" and d.superId= ? ");
        if(StringUtils.isNotBlank(district.getSuperId())){
            list.add(district.getSuperId());
        }else{
            list.add("0");
        }
        if(StringUtils.isNotBlank(district.getDisName())){
            sb.append(" and d.disName like ? ");
            list.add("%"+district.getDisName()+"%");
        }
        Long total = (Long) dao.findUniqueOne("select count(*) "+sb.toString(), list);
        //排序
        if(StringUtils.isNotBlank(pageParam.getSort())){
            pageParam.appendOrderBy(sb);
        }else{
            sb.append(" order by d.disSort asc");
        }
        List<SyDistrict> rows = dao.findPage(sb.toString(),pageParam.getPage(),pageParam.getRows(),list);
        dataGrid.setTotal(total);
        dataGrid.setRows(rows);
        return dataGrid;
    }

    /**
     * 添加地区
     * @param district
     * @return
     */
    @Override
    public String addDistrict(SyDistrict district) {
        Object obj = dao.findOne("from SyDistrict where superId=? and disName=?", district.getSuperId(), district.getDisName());
        if(obj==null){
            return dao.save(district)?MsgConfig.MSG_KEY_SUCCESS:MsgConfig.MSG_KEY_FAIL;//三元运算符
        }else{
            return "msg.district.unique";//名称重复！同级下已有此名称！
        }
    }


}
