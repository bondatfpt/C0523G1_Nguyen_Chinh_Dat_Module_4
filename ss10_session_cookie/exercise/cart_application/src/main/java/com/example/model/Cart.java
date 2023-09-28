package com.example.model;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<Product, Integer> productsMap = new HashMap<>();

    public Cart() {
    }

    public Cart(Map<Product, Integer> productsMap) {
        this.productsMap = productsMap;
    }

    public Map<Product,Integer> getAll (){
        return productsMap;
    }

    public void addProduct (Product product){
        if(productsMap.containsKey(product)){
            Integer amount = productsMap.get(product);
            productsMap.replace(product,amount + 1);
        }else {
            productsMap.put(product,1);
        }
    }
    public Integer countQuantityProduct (){
        Integer quantity = 0;
        for (Map.Entry<Product,Integer> entry:productsMap.entrySet()) {
            quantity += entry.getValue();
        }
        return quantity;
    }


    public double countTotalPayment (){
        double totalPayment = 0;
        for (Map.Entry<Product,Integer> entry: productsMap.entrySet()) {
            totalPayment += entry.getValue() * (entry.getKey().getPrice());
        }
        return totalPayment;
    }
}
