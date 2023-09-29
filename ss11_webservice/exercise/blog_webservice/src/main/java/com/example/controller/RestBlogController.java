package com.example.controller;

import com.example.model.Blog;
import com.example.service.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class RestBlogController {
    @Autowired
    private IBlogService iBlogService;

    @GetMapping("/api/blogs")
    public ResponseEntity<List<Blog>> getListBlog (){
        return new ResponseEntity<>((List<Blog>) iBlogService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/api/blogs/{id}")
    public ResponseEntity<Blog> showDetails (@PathVariable int id){
        Blog blog = iBlogService.findById(id);
        if (blog == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(blog,HttpStatus.OK);
        }
    }
}
