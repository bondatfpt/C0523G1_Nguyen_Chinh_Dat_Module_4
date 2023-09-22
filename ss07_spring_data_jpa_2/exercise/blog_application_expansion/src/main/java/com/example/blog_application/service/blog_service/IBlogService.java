package com.example.blog_application.service.blog_service;

import com.example.blog_application.model.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IBlogService {
    Page<Blog> findAll(Pageable pageable);
    boolean save (Blog blog);
    Blog findById (int id);
    boolean delete (int id);
    List<Blog> showBlogByCategoryId(int categoryId);
    List<Blog> searchByTittle (String tittle);
}
