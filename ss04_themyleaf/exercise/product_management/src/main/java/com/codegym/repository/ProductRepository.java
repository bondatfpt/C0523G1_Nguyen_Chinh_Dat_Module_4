package com.codegym.repository;

import com.codegym.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository implements IProductRepository {
    private static List<Product> productList = new ArrayList<>();

    static {
        productList.add(new Product(1, "SamSungA32", 5000, "Good", "SamSung"));
        productList.add(new Product(2, "SamSungA35", 4000, "Good", "SamSung"));
        productList.add(new Product(3, "SamSungA37", 3000, "Good", "SamSung"));
        productList.add(new Product(4, "SamSungA38", 2000, "Good", "SamSung"));
    }

    @Override
    public List<Product> getAll() {
        return productList;
    }

    @Override
    public void save(Product product) {
        productList.add(product);
    }

    @Override
    public Product getProductById(int id) {
        for (Product product : productList) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    @Override
    public void update(Product product, int id) {
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getId() == id) {
                productList.set(i, product);
                break;
            }
        }
    }

    @Override
    public void delete(int id) {
        Product product = getProductById(id);
        productList.remove(product);
    }

    @Override
    public List<Product> searchByName(String name) {
        name = name.toLowerCase().trim();
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < productList.size(); i++) {
            if(productList.get(i).getName().toLowerCase().trim().contains(name)){
                products.add(productList.get(i));
            }
        }
        return products;
    }
}