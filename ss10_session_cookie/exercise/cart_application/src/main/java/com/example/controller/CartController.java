package com.example.controller;

import com.example.model.Cart;
import com.example.model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import java.util.Map;

@Controller
public class CartController {
    @GetMapping("showCart")
    public String showCart (@SessionAttribute(value = "cart", required = false)
                                Cart cart, Model model){
        Map<Product,Integer> productIntegerMap = cart.getAll();
        model.addAttribute("listCart",productIntegerMap);
        double totalPayment = cart.countTotalPayment();
        model.addAttribute("totalPayment",totalPayment);
        int quantity = cart.countQuantityProduct();
        model.addAttribute("quantity",quantity);
        return "list-cart";
    }

    @GetMapping("/deleteProduct")
    public String deleteProduct (@RequestParam int id,Model model, @SessionAttribute
                                    (value ="cart",required = false) Cart cart){
        Map<Product,Integer> productIntegerMap = cart.getAll();
        for (Product product:productIntegerMap.keySet()) {
            if (product.getId() == id){
                productIntegerMap.remove(product,productIntegerMap.get(product));
                break;
            }
        }
        return showCart(cart,model);
    }
    @PostMapping("/changeAmount")
    public String changeAmount(@SessionAttribute(value = "cart",required = false)Cart cart, Model model,
                               @RequestParam int idChangeAmount, @RequestParam int amount){
        Map<Product,Integer> productIntegerMap = cart.getAll();
        for (Product product:productIntegerMap.keySet()) {
            if(product.getId() == idChangeAmount){
                productIntegerMap.replace(product,amount);
                break;
            }
        }
        return showCart(cart,model);
    }
}