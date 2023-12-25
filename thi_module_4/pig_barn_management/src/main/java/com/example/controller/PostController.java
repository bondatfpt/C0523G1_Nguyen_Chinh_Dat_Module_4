package com.example.controller;

import com.example.dto.PostDto;
import com.example.model.Category;
import com.example.model.Post;
import com.example.service.ICategoryService;
import com.example.service.IPostService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class PostController {
    @Autowired
    private IPostService iPostService;
    @Autowired
    private ICategoryService iCategoryService;
    @GetMapping("")
    public String showList (@PageableDefault(value = 0, size = 2)Pageable pageable, Model model){
        Page<Post> posts = iPostService.findAll(PageRequest.of(pageable.getPageNumber(),pageable.getPageSize()));
        model.addAttribute("list", posts);
        return "list";
    }

    @GetMapping("/showFormCreate")
    public String showFormCreate (Model model){
        PostDto postDto = new PostDto();
        model.addAttribute("postCreate",postDto);
        List<Category> categories = iCategoryService.findAll();
        model.addAttribute("categoryList",categories);
        return "create";
    }
    @PostMapping("/create")
    public String create (@Valid  @ModelAttribute("postCreate") PostDto postDto, BindingResult bindingResult,Model model){
        if (bindingResult.hasFieldErrors()){
            List<Category> categories = iCategoryService.findAll();
            model.addAttribute("categoryList",categories);
            return "create";
        }else {
            Post post = new Post();
            BeanUtils.copyProperties(postDto,post);
            iPostService.save(post);
        }
        return "redirect:/";
    }

    @PostMapping ("/delete")
    public String delete (@RequestParam Integer idDelete){
        iPostService.delete(idDelete);
        return "redirect:/";
    }

    @GetMapping("/detail/{id}")
    public String showDetail (@PathVariable Integer id,Model model){
        Post post = iPostService.findById(id);
        model.addAttribute("postDetail",post);
        return "detail";
    }
}
