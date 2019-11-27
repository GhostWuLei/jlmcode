package com.oa.manager.system.service.impl;

import com.oa.commons.base.BaseServiceImpl;
import com.oa.manager.system.bean.ListValues;
import com.oa.manager.system.service.IListValuesService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created By WL on 2019/11/22
 */
@Service
public class ListValuesServiceImpl extends BaseServiceImpl implements IListValuesService{


    @Override
    public List<ListValues> selectListByType(Integer type) {
        return null;
    }
}
