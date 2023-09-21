package com.example.blog_application.controller;

import com.example.blog_application.model.Blog;
import com.example.blog_application.service.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class BlogController {
    @Autowired
    private IBlogService iBlogService;
    @GetMapping("")
    public String showList(Model model){
        List<Blog> blogList = iBlogService.findAll();
        model.addAttribute("list",blogList);
        return "list";
    }
    @GetMapping("showFormCreate")
    public String showFormCreate(Model model){
        model.addAttribute("blog",new Blog());
        return "form-create";
    }
    @PostMapping("create")
    public String create (Blog blog){
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE;
        blog.setDateOfWriting(localDate.format(dateTimeFormatter));
        iBlogService.save(blog);
        return "redirect:/";
    }
    @GetMapping("showFormUpdate")
    public String showFormUpdate(@RequestParam int id,Model model){
        Blog blog = iBlogService.findById(id);
        model.addAttribute("blog",blog);
        return "form-update";
    }
    @PostMapping("update")
    public String update (Blog blog){
        iBlogService.save(blog);
        return "redirect:/";
    }
    @GetMapping("showDetails")
    public String showDetails (@RequestParam int id, Model model){
        Blog blog = iBlogService.findById(id);
        model.addAttribute("blogDetails",blog);
        return "form-details";
    }
    @PostMapping("delete")
    public String delete (@RequestParam int idDelete){
        iBlogService.delete(idDelete);
        return "redirect:/";
    }
}
