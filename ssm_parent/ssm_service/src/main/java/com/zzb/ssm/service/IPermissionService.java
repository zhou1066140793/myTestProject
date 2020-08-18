package com.zzb.ssm.service;

import com.zzb.ssm.domain.Permission;

import java.util.List;

public interface IPermissionService {
    List<Permission> findAll();

    void save(Permission permission);

    List<Permission> findOtherByRoleId(String roleId);
}
