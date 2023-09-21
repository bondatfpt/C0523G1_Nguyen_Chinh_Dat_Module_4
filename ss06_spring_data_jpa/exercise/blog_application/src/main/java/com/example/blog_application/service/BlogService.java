package com.example.blog_application.service;

import com.example.blog_application.model.Blog;
import com.example.blog_application.repository.IBlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BlogService implements IBlogService{
    @Autowired
    private IBlogRepository iBlogRepository;
    @Override
    public List<Blog> findAll() {
        return (List<Blog>) iBlogRepository.findAll();
    }

    @Override
    public boolean save(Blog blog) {
        try {
            iBlogRepository.save(blog);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public Blog findById(int id) {
        return iBlogRepository.findById(id).get();
    }

    @Override
    public boolean delete(int id) {
        try{
            iBlogRepository.deleteById(id);
        }catch (Exception e){
            return false;
        }
        return true;
    }
}
