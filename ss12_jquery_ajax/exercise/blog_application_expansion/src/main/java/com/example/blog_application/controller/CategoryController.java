package com.example.blog_application.controller;

import com.example.blog_application.model.Blog;
import com.example.blog_application.model.Category;
import com.example.blog_application.repository.IBlogRepository;
import com.example.blog_application.service.blog_service.IBlogService;
import com.example.blog_application.service.category_service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CategoryController {
    @Autowired
    ICategoryService iCategoryService;
    @Autowired
    IBlogService iBlogService;
    @GetMapping("showListCategory")
    public String showListCategory(Model model) {
        List<Category> categoryList = iCategoryService.findAll();
        model.addAttribute("categoryList",categoryList);
        return "category-list";
    }
    @GetMapping(" showFormCreateCategory")
    public String showFormCreateCategory(Model model){
        model.addAttribute("category",new Category());
        return "form-create-category";
    }

    @PostMapping("createCategory")
    public String create (Category category){
        iCategoryService.saveCategory(category);
        return "redirect:/showListCategory";
    }

    @GetMapping ("showFormUpdateCategory")
    public String showFormUpdateCategory (@RequestParam int id,Model model){
        Category category = iCategoryService.findById(id);
        model.addAttribute("categoryUpdate",category);
        return "form-update-category";
    }

    @PostMapping ("updateCategory")
    public String update (Category category){
        iCategoryService.saveCategory(category);
        return "redirect:/showListCategory";
    }

    @PostMapping ("deleteCategory")
    public String delete (@RequestParam int idDelete){
        iCategoryService.deleteCategory(idDelete);
        return "redirect:/showListCategory";
    }

    @GetMapping ("showBlogByCategoryId")
    public ModelAndView showBlogByCategoryId (@RequestParam int categoryId, Model model){
        List <Blog> blogList = iBlogService.showBlogByCategoryId(categoryId);
        boolean flag = false;
        ModelAndView modelAndView = new ModelAndView("list","blogList",blogList);
        modelAndView.addObject("flag",flag);
        return modelAndView;
    }

}
