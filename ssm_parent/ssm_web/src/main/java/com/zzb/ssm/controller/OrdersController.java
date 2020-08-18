package com.zzb.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.zzb.ssm.domain.Orders;
import com.zzb.ssm.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {
    @Autowired
    private IOrdersService ordersService;

    @RequestMapping("/findById.do")
    public ModelAndView findById(String id){
        ModelAndView modelAndView = new ModelAndView();
        Orders byId = ordersService.findById(id);
        modelAndView.addObject("orders" , byId);
        modelAndView.setViewName("orders-show");
        return modelAndView;
    }

    @RequestMapping("/findAll.do")
    public ModelAndView findAllOrders(
            @RequestParam(name = "page" , defaultValue = "1")Integer page ,
          @RequestParam(name = "pageSize" , defaultValue = "4")Integer pageSize){
        ModelAndView modelAndView = new ModelAndView();
        List<Orders> allByPage = ordersService.findAllByPage(page,pageSize);
        PageInfo pageInfo = new PageInfo(allByPage);
        modelAndView.setViewName("orders-page-list");
        modelAndView.addObject("pageInfo",pageInfo);
//        modelAndView.setViewName("orders-list");
//        modelAndView.addObject("ordersList",allByPage);
        return modelAndView;
    }
}
