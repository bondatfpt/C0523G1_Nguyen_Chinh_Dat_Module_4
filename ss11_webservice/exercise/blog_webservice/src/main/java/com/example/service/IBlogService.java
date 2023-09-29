package com.example.service;

import com.example.model.Blog;

import java.util.List;

public interface IBlogService {
    List<Blog> findAll ();
    Blog findById (int id);
    List<Blog> showBlogByCategoryId(int categoryId);
}
