package com.example.service;

import com.example.model.Product;

import java.util.List;
import java.util.Map;

public interface IProductService {
    List<Product> findAll ();
    Product findById (Integer id);
    boolean save (Product product);
    void pay(Map<Product,Integer>productsMap);
}
