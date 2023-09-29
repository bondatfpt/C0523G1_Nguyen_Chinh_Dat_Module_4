package com.example.controller;

import com.example.model.Blog;
import com.example.model.Category;
import com.example.service.IBlogService;
import com.example.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestCategoryController {
    @Autowired
    private ICategoryService iCategoryService;
    @Autowired
    private IBlogService iBlogService;

    @GetMapping("/api/categories")
    public ResponseEntity<List<Category>> getAllCategory(){
        return new ResponseEntity<>((List<Category>) iCategoryService.findAll(),HttpStatus.OK);
    }
    @GetMapping("/api/categories/{id}")
    public ResponseEntity<List<Blog>> showBlogByCategoryId(@PathVariable int id){
        List <Blog> blogList = iBlogService.showBlogByCategoryId(id);
        if(blogList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(blogList,HttpStatus.OK);
        }
    }
}
