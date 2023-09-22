package com.example.blog_application.repository;

import com.example.blog_application.model.Category;
import org.springframework.data.repository.CrudRepository;

public interface ICategoryRepository extends CrudRepository<Category,Integer> {
}
