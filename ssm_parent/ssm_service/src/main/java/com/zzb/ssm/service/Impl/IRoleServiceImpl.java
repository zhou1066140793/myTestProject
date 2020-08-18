package com.zzb.ssm.service.Impl;

import com.zzb.ssm.dao.IRoleDao;
import com.zzb.ssm.domain.Role;
import com.zzb.ssm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IRoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleDao roleDao;

    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    @Override
    public void save(Role role) {
        roleDao.save(role);
    }

    @Override
    public List<Role> findOther(String uId) {
        return roleDao.findByUserIdOther(uId);
    }

    @Override
    public Role findRoleById(String roleId) {
        return roleDao.findRoleById(roleId);
    }

    @Override
    public void addPermissionToRole(String roleId, String[] permissionIds) {
        for (String permissionId : permissionIds) {
            roleDao.addPermissionToRole(roleId,permissionId);
        }
    }
}
