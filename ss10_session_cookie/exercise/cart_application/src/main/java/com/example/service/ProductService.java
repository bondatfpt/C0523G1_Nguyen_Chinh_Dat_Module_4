package com.example.service;

import com.example.model.Product;
import com.example.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class ProductService implements IProductService {
    @Autowired
    private IProductRepository iProductRepository;


    @Override
    public List<Product> findAll() {
        return iProductRepository.findAll();
    }

    @Override
    public Product findById(Integer id) {
        return iProductRepository.findById(id).get();
    }

    @Override
    public boolean save(Product product) {
        try {
            iProductRepository.save(product);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public void pay(Map<Product, Integer> productsMap) {
        if (productsMap.isEmpty()) {
            throw new RuntimeException("Giỏ hàng trống !");
        }
        for (Product productPurchased : productsMap.keySet()) {
            Product productInventory = findById(productPurchased.getId());
            productInventory.setAmount(productInventory.getAmount() - productsMap.get(productInventory));
            save(productInventory);
        }
        productsMap.clear();
    }
}
