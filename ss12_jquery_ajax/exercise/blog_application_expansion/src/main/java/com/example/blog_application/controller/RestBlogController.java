package com.example.blog_application.controller;

import com.example.blog_application.model.Blog;
import com.example.blog_application.service.blog_service.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin("*")
public class RestBlogController {
    @Autowired
    private IBlogService iBlogService;

    @GetMapping("/api/blogs")
    public ResponseEntity<Page<Blog>>showList(@PageableDefault(value = 0, size = 2) Pageable pageable){
        Page<Blog> blogList = iBlogService.findAll(PageRequest.of(pageable.getPageNumber(),pageable.getPageSize(),
                Sort.by("dateOfWriting").descending()));
       if(blogList.isEmpty()){
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }else {
           return new ResponseEntity<>(blogList,HttpStatus.OK);
       }
    }

    @GetMapping ("/api/blogs/search/{tittleSearch}")
    public ResponseEntity<List<Blog>> searchByTittle (@PathVariable String tittleSearch){
        List <Blog> blogList= iBlogService.searchByTittle(tittleSearch);
        if (blogList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(blogList,HttpStatus.OK);
        }
    }
}
