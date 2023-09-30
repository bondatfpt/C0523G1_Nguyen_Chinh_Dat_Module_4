package com.example.repository;

import com.example.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface IProductRepository extends JpaRepository<Product,Integer>{
    @Query (value = "select SUM(amount) from products",nativeQuery = true)
    Integer countAmountProductInventory ();
}
