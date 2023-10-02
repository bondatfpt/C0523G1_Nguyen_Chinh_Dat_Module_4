package com.example.blog_application.service.category_service;

import com.example.blog_application.model.Category;
import com.example.blog_application.repository.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    ICategoryRepository iCategoryRepository;

    @Override
    public List<Category> findAll() {
        return (List<Category>) iCategoryRepository.findAll();
    }

    @Override
    public boolean saveCategory(Category category) {
        try {
            iCategoryRepository.save(category);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public Category findById(int id) {
        return iCategoryRepository.findById(id).get();
    }

    @Override
    public boolean deleteCategory(int id) {
        try {
            iCategoryRepository.deleteById(id);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
