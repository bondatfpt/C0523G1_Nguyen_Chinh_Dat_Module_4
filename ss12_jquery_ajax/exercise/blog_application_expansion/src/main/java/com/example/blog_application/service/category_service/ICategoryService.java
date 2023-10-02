package com.example.blog_application.service.category_service;

import com.example.blog_application.model.Category;

import java.util.List;

public interface ICategoryService {
    List<Category> findAll ();
    boolean saveCategory (Category category);

    Category findById (int id);
    boolean deleteCategory (int id);
}
