package com.oa.manager.system.service.impl;

import com.oa.commons.base.BaseServiceImpl;
import com.oa.manager.system.service.IRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created By WL on 2019/11/22
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl implements IRoleService{

    @Override
    public List<String> selectRolesByUserId(String userId) {
        return null;
    }
}
