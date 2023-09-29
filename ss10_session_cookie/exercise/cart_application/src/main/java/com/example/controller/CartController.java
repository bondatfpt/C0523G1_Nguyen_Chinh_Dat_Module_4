package com.example.controller;

import com.example.model.Cart;
import com.example.model.Product;
import com.example.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.Map;

@Controller
public class CartController {
    @Autowired
    private IProductService iProductService;

    @GetMapping("showCart")
    public String showCart(@SessionAttribute(value = "cart", required = false)
                           Cart cart, Model model) {
        Map<Product, Integer> productIntegerMap = cart.getAll();
        model.addAttribute("listCart", productIntegerMap);
        double totalPayment = cart.countTotalPayment();
        model.addAttribute("totalPayment", totalPayment);
        int quantity = cart.countQuantityProduct();
        model.addAttribute("quantity", quantity);
        return "list-cart";
    }

    @GetMapping("/deleteProduct")
    public String deleteProduct(@RequestParam Integer id, Model model, @SessionAttribute
            (value = "cart", required = false) Cart cart) {
        cart.deleteProduct(id);
        return showCart(cart, model);
    }

    @PostMapping("/changeAmount")
    public String changeAmount(@SessionAttribute(value = "cart", required = false) Cart cart, Model model,
                               @RequestParam Integer idChangeAmount, @RequestParam Integer amount) {
        cart.changeAmount(idChangeAmount,amount);
        return showCart(cart, model);
    }

    @GetMapping("/pay")
    public String pay(Model model, @SessionAttribute(value = "cart", required = false) Cart cart) {
        Map<Product, Integer> productIntegerMap = cart.getAll();
        for (Product product : productIntegerMap.keySet()) {
            Product product1 = iProductService.findById(product.getId());
            product1.setAmount(product1.getAmount() - productIntegerMap.get(product));
            iProductService.save(product1);
        }
        productIntegerMap.clear();
        return "redirect:/";
    }
}
