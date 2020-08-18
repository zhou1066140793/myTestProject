package com.zzb.ssm.service;

import com.zzb.ssm.domain.Role;

import java.util.List;

public interface IRoleService {

    List<Role> findAll();

    void save(Role role);

    List<Role> findOther(String uId);

    Role findRoleById(String roleId);

    void addPermissionToRole(String roleId, String[] permissionIds);
}
