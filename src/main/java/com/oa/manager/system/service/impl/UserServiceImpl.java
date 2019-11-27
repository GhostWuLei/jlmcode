package com.oa.manager.system.service.impl;

import com.oa.commons.base.BaseServiceImpl;
import com.oa.manager.system.service.IUserService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;

/**
 * Created By WL on 2019/11/22
 */
@Service
public class UserServiceImpl extends BaseServiceImpl implements IUserService{

    @Override
    public Map<String, Collection<String>> selectRolesPowers(String userId) {
        return null;
    }


}
