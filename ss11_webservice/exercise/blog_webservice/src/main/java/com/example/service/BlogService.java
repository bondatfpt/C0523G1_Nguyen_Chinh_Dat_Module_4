package com.example.service;

import com.example.model.Blog;
import com.example.repository.IBLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BlogService implements IBlogService{
    @Autowired
    private IBLogRepository ibLogRepository;

    @Override
    public Blog findById(int id) {
        return ibLogRepository.findById(id).orElse(null);
    }

    @Override
    public List<Blog> showBlogByCategoryId(int categoryId) {
        return ibLogRepository.showBlogByCategory(categoryId);
    }

    @Override
    public List<Blog> searchBlogByTittle(String title) {
        List<Blog> blogList = ibLogRepository.searchBlogByTittle("%" + title + "%");
        return blogList;
    }

    @Override
    public List<Blog> findAll() {
        return ibLogRepository.findAll();
    }
}
