package com.zzb.ssm.service.Impl;

import com.github.pagehelper.PageHelper;
import com.zzb.ssm.dao.IOrderDao;
import com.zzb.ssm.domain.Orders;
import com.zzb.ssm.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IOrdersServiceImpl implements IOrdersService {

    @Autowired
    private IOrderDao orderDao;


    @Override
    public Orders findById(String id) {
        return orderDao.findById(id);
    }

    @Override
    public List<Orders> findAllByPage(Integer page , Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        return orderDao.findAllByPage();
    }
}
