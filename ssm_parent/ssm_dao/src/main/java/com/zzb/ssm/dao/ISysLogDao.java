package com.zzb.ssm.dao;

import com.zzb.ssm.domain.SysLog;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ISysLogDao {

    @Select("select * from syslog")
    @Results(id = "sysLogMap", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "visitTime", property = "visitTime"),
            @Result(column = "ip", property = "ip"),
            @Result(column = "url", property = "url"),
            @Result(column = "executionTime", property = "executionTime"),
            @Result(column = "method", property = "method"),
            @Result(column = "username", property = "username")
    })
    List<SysLog> findAll();

    @Insert("insert into syslog(visitTime,username,ip,url,executionTime,method) " +
            "values (#{visitTime},#{username},#{ip},#{url},#{executionTime},#{method})")
    void save(SysLog sysLog);

    @Select("select * from syslog")
    @ResultMap("sysLogMap")
    List<SysLog> findAll2();

}
