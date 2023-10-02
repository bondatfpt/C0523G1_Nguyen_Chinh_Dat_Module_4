package com.example.blog_application.controller;

import com.example.blog_application.model.Blog;
import com.example.blog_application.service.blog_service.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class BlogController {
    @Autowired
    private IBlogService iBlogService;
    @GetMapping("")
    public ModelAndView showList(@PageableDefault(value = 0, size = 2)Pageable pageable){
        Page<Blog> blogList = iBlogService.findAll(PageRequest.of(pageable.getPageNumber(),pageable.getPageSize(),
                Sort.by("dateOfWriting").descending()));
        boolean flag = true;
        ModelAndView modelAndView = new ModelAndView("list","blogList",blogList);
        modelAndView.addObject("flag",flag);
        return modelAndView;
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
        System.out.println(blog.getCategory().getName());
        return "form-details";
    }
    @PostMapping("delete")
    public String delete (@RequestParam int idDelete){
        iBlogService.delete(idDelete);
        return "redirect:/";
    }
    @GetMapping("search")
    public ModelAndView searchByTittle (@RequestParam String nameSearch){
        List<Blog> blogList = iBlogService.searchByTittle (nameSearch);
        boolean flag = false;
        ModelAndView modelAndView = new ModelAndView("list","blogList",blogList);
        modelAndView.addObject("flag",flag);
        return modelAndView;
    }
}
