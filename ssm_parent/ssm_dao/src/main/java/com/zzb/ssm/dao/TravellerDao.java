package com.zzb.ssm.dao;

import com.zzb.ssm.domain.Traveller;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TravellerDao {

    @Select("SELECT \n" +
            "*\n" +
            "FROM\n" +
            "traveller\n" +
            "WHERE\n" +
            "id in(\n" +
            "\t\tSELECT\n" +
            "\t\t\ttravellerId\n" +
            "\t\tFROM\n" +
            "\t\t\torder_traveller\n" +
            "\t\tWHERE\n" +
            "\t\t\torderId = #{id}\n" +
            ")\n")
    List<Traveller> findListById(String id);
}
