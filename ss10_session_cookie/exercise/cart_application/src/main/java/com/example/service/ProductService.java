package com.example.service;

import com.example.model.Cart;
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

    @Override
    public Integer countAmountProductInventory() {
        return iProductRepository.countAmountProductInventory();
    }

    public void pay(Map<Product, Integer> productsMap) {
        Cart cart = new Cart();
        if (cart.countQuantityProduct(productsMap) > countAmountProductInventory()){
            throw  new RuntimeException("Quá số lượng hàng tồn kho!");
        }
        if (cart.countQuantityProduct(productsMap) <= 0){
            throw new RuntimeException("Số lượng không hợp lệ!");
        }
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
