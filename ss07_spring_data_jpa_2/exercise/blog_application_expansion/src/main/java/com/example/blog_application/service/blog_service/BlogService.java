package com.example.blog_application.service.blog_service;

import com.example.blog_application.model.Blog;
import com.example.blog_application.repository.IBlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BlogService implements IBlogService{
    @Autowired
    private IBlogRepository iBlogRepository;
    @Override
    public Page<Blog> findAll(Pageable pageable) {
        return (Page<Blog>) iBlogRepository.findAll(pageable);
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

    @Override
    public List<Blog> showBlogByCategoryId(int categoryId) {
        return iBlogRepository.showBlogByCategory(categoryId);
    }

    @Override
    public List<Blog> searchByTittle(String tittle) {
        tittle = "%".concat(tittle).concat("%");
        return iBlogRepository.searchByTittle(tittle);
    }
}
