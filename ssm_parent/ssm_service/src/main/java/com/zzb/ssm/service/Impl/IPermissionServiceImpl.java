package com.zzb.ssm.service.Impl;

import com.zzb.ssm.dao.IPermissionDao;
import com.zzb.ssm.domain.Permission;
import com.zzb.ssm.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IPermissionServiceImpl implements IPermissionService {

    @Autowired
    private IPermissionDao permissionDao;

    @Override
    public List<Permission> findAll() {
        return permissionDao.findAll();
    }

    @Override
    public void save(Permission permission) {
        permissionDao.save(permission);
    }

    @Override
    public List<Permission> findOtherByRoleId(String roleId) {
        return permissionDao.findOtherByRoleId(roleId);
    }
}
