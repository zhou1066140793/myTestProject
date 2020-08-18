package com.zzb.ssm.dao;

import com.zzb.ssm.domain.Role;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface IRoleDao {



    @Select({"SELECT * FROM role WHERE id IN ( SELECT roleId FROM users_role WHERE userId = #{userId})"})
    @Results(id = "RoleMap", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "roleName", property = "roleName"),
            @Result(column = "roleDesc", property = "roleDesc"),
            @Result(column = "id", property = "permissions", javaType = List.class,
                    many = @Many(select = "com.zzb.ssm.dao.IPermissionDao.findByRoleId",
                            fetchType = FetchType.LAZY))
    })
    List<Role> findByUserId(String userId);

    @Select("SELECT * FROM role")
    @ResultMap("RoleMap")
    List<Role> findAll();

    @Insert("insert into")
    void save(Role role);

    @Select("SELECT * FROM role WHERE id not in ( SELECT roleId FROM users_role WHERE userId = #{userId})")
    List<Role> findByUserIdOther(String userId);

    @Select("SELECT * FROM role where id = #{roleId}")
    @ResultMap("RoleMap")
    Role findRoleById(String roleId);

    @Insert("insert into role_permission(roleId,permissionId) value(#{roleId},#{permissionId})")
    void addPermissionToRole(@Param("roleId") String roleId,@Param("permissionId") String permissionId);
}
