package com.example.dto;

import com.example.model.Product;

import java.util.HashMap;
import java.util.Map;

public class CartDto {
    private Map<Product, Integer> productsMap = new HashMap<>();

    public CartDto() {
    }

    public CartDto(Map<Product, Integer> productsMap) {
        this.productsMap = productsMap;
    }

    public Map<Product, Integer> getAll() {
        return productsMap;
    }

    public void addProduct(Product product) {
        if (productsMap.containsKey(product)) {
            Integer amount = productsMap.get(product);
            productsMap.replace(product, amount + 1);
        } else {
            productsMap.put(product, 1);
        }
    }

    public void deleteProduct(Integer id) {
        boolean flag = false;
        for (Product product : productsMap.keySet()) {
            if (product.getId() == id) {
                productsMap.remove(product, productsMap.get(product));
                flag = true;
                break;
            }
        }
        if (flag == false){
            throw new RuntimeException("Sản phẩm không tồn tại!");
        }

    }

    public void changeAmount(int idProduct, Integer amount) {
        for (Product product : productsMap.keySet()) {
            if (product.getId() == idProduct && product.getAmount() >= amount && amount >= 0) {
                productsMap.replace(product, amount);
            }
        }
    }


    public Integer countQuantityProduct(Map<Product, Integer> integerMap) {
        Integer quantity = 0;
        for (Map.Entry<Product, Integer> entry : integerMap.entrySet()) {
            quantity += entry.getValue();
        }
        return quantity;
    }

    public double countTotalPayment() {
        double totalPayment = 0;
        for (Map.Entry<Product, Integer> entry : productsMap.entrySet()) {
            totalPayment += entry.getValue() * (entry.getKey().getPrice());
        }
        return totalPayment;
    }
}
