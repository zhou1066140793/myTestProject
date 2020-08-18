package com.zzb.ssm.dao;


import com.zzb.ssm.domain.Orders;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IOrderDao {

    @Results(id = "orderMap", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "orderNum", property = "orderNum"),
            @Result(column = "orderTime", property = "orderTime"),
            @Result(column = "orderStatus", property = "orderStatus"),
            @Result(column = "peopleCount", property = "peopleCount"),
            @Result(column = "payType", property = "payType"),
            @Result(column = "orderDesc", property = "orderDesc"),
            @Result(column = "productId", property = "product",
                    one = @One(select = "com.zzb.ssm.dao.IProductDao.findById")),
            @Result(column = "memberId" , property = "member",
                    one = @One(select = "com.zzb.ssm.dao.IMemberDao.findById")),
            @Result(column = "id" , property = "travellers" ,
                    many = @Many(select = "com.zzb.ssm.dao.TravellerDao.findListById"))
    })

    @Select("SELECT * FROM orders")
    List<Orders> findAllByPage();

    @Select("SELECT * FROM orders where id = #{id}")
    @ResultMap("orderMap")
    Orders findById(String id);
}
