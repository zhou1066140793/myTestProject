package com.zzb.ssm.service;

import com.zzb.ssm.domain.Product;

import java.util.List;

public interface IProductService {

    void save(Product product);

    List<Product> findAll();
}
