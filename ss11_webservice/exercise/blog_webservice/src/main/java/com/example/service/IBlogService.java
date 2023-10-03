package com.example.service;

import com.example.model.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IBlogService {
    Blog findById (int id);
    List<Blog> showBlogByCategoryId(int categoryId);

    List<Blog> searchBlogByTittle (String title);
    List<Blog> findAll ();
}
