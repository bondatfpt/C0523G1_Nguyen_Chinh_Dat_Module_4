package com.example.controller;

import com.example.model.Blog;
import com.example.service.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

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

    @GetMapping("/api/blogs/{id}")
    public ResponseEntity<Blog> showDetails (@PathVariable int id){
        Blog blog = iBlogService.findById(id);
        if (blog == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(blog,HttpStatus.OK);
        }
    }
    @GetMapping ("/api/blogs/search/{tittleSearch}")
    public ResponseEntity<List<Blog>> searchByTittle (@PathVariable String tittleSearch){
        List <Blog> blogList= iBlogService.searchBlogByTittle(tittleSearch);
        if (blogList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(blogList,HttpStatus.OK);
        }
    }
}
