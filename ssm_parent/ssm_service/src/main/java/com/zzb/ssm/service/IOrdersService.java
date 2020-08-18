package com.zzb.ssm.service;

import com.zzb.ssm.domain.Orders;

import java.util.List;

public interface IOrdersService {

    Orders findById(String id);

    List<Orders> findAllByPage(Integer page , Integer pageSize);
}
