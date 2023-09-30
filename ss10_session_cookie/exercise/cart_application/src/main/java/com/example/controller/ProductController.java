package com.example.controller;

import com.example.model.Cart;
import com.example.model.Product;
import com.example.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/products")
@SessionAttributes("cart")
public class ProductController {
    @ModelAttribute("cart")
    public Cart initCart() {
        return new Cart();
    }

    @Autowired
    private IProductService iProductService;

    @GetMapping("")
    public String showListProduct(Model model) {
        List<Product> productList = iProductService.findAll();
        model.addAttribute("list", productList);
        return "list";
    }

    @GetMapping("/details/{id}")
    public String showFormDetails(@PathVariable Integer id, Model model) {
        Product product = iProductService.findById(id);
        model.addAttribute("productDetails", product);
        return "form-details";
    }

    @GetMapping("/addToCart/{id}")
    public String addToCart (@PathVariable Integer id, @SessionAttribute(value = "cart", required = false) Cart cart){
        Product product = iProductService.findById(id);
        cart.addProduct(product);
        return "redirect:/carts";
    }

}
