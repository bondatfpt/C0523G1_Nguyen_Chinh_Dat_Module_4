package com.codegym.repository;

import com.codegym.model.Product;

import java.util.List;

public interface IProductRepository {
    List<Product> getAll ();
    void save (Product product);
    Product getProductById(int id);
    boolean update (Product product,int id);
    void delete (int id);
    List<Product> searchByName (String name);
}
