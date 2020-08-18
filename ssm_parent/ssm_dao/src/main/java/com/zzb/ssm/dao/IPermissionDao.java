package com.zzb.ssm.dao;

import com.zzb.ssm.domain.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IPermissionDao {

    @Select("SELECT * FROM permission WHERE id IN (SELECT permissionId FROM role_permission WHERE roleId = #{roleId})")
    List<Permission> findByRoleId(String roleId);

    @Select("SELECT * FROM permission")
    List<Permission> findAll();

    @Insert("insert into permission(permissionName,url) value(#{permissionName},#{url})")
    void save(Permission permission);

    @Select("SELECT * FROM permission WHERE id not IN (SELECT permissionId FROM role_permission WHERE roleId = #{roleId})")
    List<Permission> findOtherByRoleId(String roleId);
}
