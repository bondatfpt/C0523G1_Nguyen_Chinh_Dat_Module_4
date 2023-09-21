package com.example.blog_application.repository;

import com.example.blog_application.model.Blog;
import org.springframework.data.repository.CrudRepository;

public interface IBlogRepository extends CrudRepository<Blog,Integer> {
}
