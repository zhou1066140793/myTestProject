package com.zzb.ssm.controller;


import com.zzb.ssm.domain.Product;
import com.zzb.ssm.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private IProductService productService;

    @RequestMapping("/save.do")
    public String save(Product product){
        productService.save(product);
        return "redirect:findAll.do";
    }

    @RequestMapping("/findAll.do")
    public ModelAndView productFindAll(){
        List<Product> productList = productService.findAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("productList",productList);
        modelAndView.setViewName("product-list1");
        return modelAndView;
    }
}
