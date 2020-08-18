package com.zzb.ssm.dao;

import com.zzb.ssm.domain.UserInfo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface IUserDao {

    @Results(id = "userInfoMap", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "username", property = "username"),
            @Result(column = "email", property = "email"),
            @Result(column = "password", property = "password"),
            @Result(column = "phoneNum", property = "phoneNum"),
            @Result(column = "status", property = "status"),
            @Result(column = "id", property = "roles", javaType = java.util.List.class,
                    many = @Many(select = "com.zzb.ssm.dao.IRoleDao.findByUserId",
                            fetchType= FetchType.LAZY))
    })

    @Select("select * from users")
    List<UserInfo> findAll();

    @Select("select * from users where username = #{username}")
    @ResultMap("userInfoMap")
    UserInfo findByName(String username);

    @Insert("insert into users(email,username,password,phoneNum,status) " +
            "value(#{email},#{username},#{password},#{phoneNum},#{status})")
    void save(UserInfo user);

    @Select("select * from users where id = #{id}")
    @ResultMap("userInfoMap")
    UserInfo findById(String id);

    @Insert("insert into users_role(userId,roleId) value(#{userId},#{roleId})")
    void addRoleToUser(@Param("userId") String userId, @Param("roleId") String roleId);
}
