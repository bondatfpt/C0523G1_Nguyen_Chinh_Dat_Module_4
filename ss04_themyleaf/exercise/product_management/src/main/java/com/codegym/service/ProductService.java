package com.codegym.service;

import com.codegym.model.Product;
import com.codegym.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService{
    @Autowired
    private IProductRepository iProductRepository;
    @Override
    public List<Product> getAll() {
        List<Product> productList = iProductRepository.getAll();
        return productList;
    }

    @Override
    public void save(Product product) {
        iProductRepository.save(product);
    }

    @Override
    public Product getProductById(int id) {
        Product product = iProductRepository.getProductById(id);
        return product;
    }

    @Override
    public void update(Product product, int id) {
        iProductRepository.update(product,id);
    }

    @Override
    public void delete(int id) {
        iProductRepository.delete(id);
    }

    @Override
    public List<Product> searchByName(String name) {
        List<Product> products = iProductRepository.searchByName(name);
        return products;
    }
}
