package com.codegym.repository;

import com.codegym.model.Product;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class ProductRepository implements IProductRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Product> getAll() {
        TypedQuery<Product> query = entityManager.createQuery("from Product", Product.class);
        return query.getResultList();
    }

    @Transactional
    @Override
    public void save(Product product) {
        entityManager.persist(product);
    }

    @Override
    public Product getProductById(int id) {
        return entityManager.find(Product.class, id);

    }

    @Transactional
    @Override
    public boolean update(Product product, int id) {
        try {
            Product productEntity = getProductById(id);
            productEntity.setName(product.getName());
            productEntity.setDescription(product.getDescription());
            productEntity.setManufacturer(product.getManufacturer());
            productEntity.setPrice(product.getPrice());
            entityManager.merge(productEntity);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Transactional
    @Override
    public void delete(int id) {
        Product product = getProductById(id);
        entityManager.remove(product);
    }

    @Override
    public List<Product> searchByName(String name) {
        TypedQuery<Product> query = entityManager.createQuery("from Product where name =:name", Product.class);
        query.setParameter("name", name);
        return query.getResultList();
    }
}