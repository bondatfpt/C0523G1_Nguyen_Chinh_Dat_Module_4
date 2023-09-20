package com.codegym.controller;

import com.codegym.model.Product;
import com.codegym.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private IProductService iProductService;
    @GetMapping("")
    public String showList(Model model){
        List<Product> productList = iProductService.getAll();
        model.addAttribute("list",productList);
        return "list";
    }
    @GetMapping("/showFormCreate")
    public String showFormCreate(Model model){
        model.addAttribute("product",new Product());
        return "form-create";
    }
    @PostMapping("/save")
    public String save (Product product){
        System.out.println(product);
        iProductService.save(product);
        return "redirect:/";
    }
    @GetMapping("/showFormUpdate")
    public String showFormUpdate (@RequestParam int id, Model model){
        Product product = iProductService.getProductById(id);
        model.addAttribute("productUpdate",product);
        return "form-update";
    }
    @PostMapping("/update")
    public String update (Product product){
        iProductService.update(product, product.getId());
        return "redirect:/";
    }
    @PostMapping ("/delete")
    public String delete (@RequestParam int idDelete,Model model){
        iProductService.delete(idDelete);
        showList(model);
        return "redirect:/";
    }
    @GetMapping("/showDetails")
    public String showDetails (@RequestParam int id, Model model){
        Product product = iProductService.getProductById(id);
        model.addAttribute("productDetails",product);
        return "show-details";
    }

    @GetMapping("/search")
    public String search (@RequestParam String nameSearch,Model model){
        List<Product> productList = iProductService.searchByName(nameSearch);
        model.addAttribute("list",productList);
        return "list";
    }
}
