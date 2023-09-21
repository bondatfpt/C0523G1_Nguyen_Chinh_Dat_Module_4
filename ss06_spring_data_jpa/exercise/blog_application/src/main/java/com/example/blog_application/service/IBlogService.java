package com.example.blog_application.service;

import com.example.blog_application.model.Blog;

import java.util.List;

public interface IBlogService {
    List<Blog> findAll();
    void save (Blog blog);
    Blog findById (int id);
    boolean delete (int id);
}
