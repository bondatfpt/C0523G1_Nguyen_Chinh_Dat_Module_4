package com.example.controller;

import com.example.model.Cart;
import com.example.model.Product;
import com.example.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/carts")
public class CartController {
    @Autowired
    private IProductService iProductService;


    @GetMapping("")
    public String showCart(@SessionAttribute(value = "cart", required = false)
                           Cart cart, Model model) {
        Map<Product, Integer> productIntegerMap = cart.getAll();
        model.addAttribute("listCart", productIntegerMap);
        if (productIntegerMap.isEmpty()) {
            model.addAttribute("message", "Giỏ hàng hiện đang trống !");
        }
        double totalPayment = cart.countTotalPayment();
        model.addAttribute("totalPayment", totalPayment);
        int quantity = cart.countQuantityProduct(productIntegerMap);
        model.addAttribute("quantity", quantity);
        return "list-cart";
    }

    @GetMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable Integer id, Model model, @SessionAttribute
            (value = "cart", required = false) Cart cart) {
        cart.deleteProduct(id);
        return showCart(cart, model);
    }

    @GetMapping("/changeAmount/{idProduct}/{amount}")
    public String changeAmount(@SessionAttribute(value = "cart", required = false) Cart cart, Model model,
                               @PathVariable Integer idProduct, @PathVariable Integer amount) {
        Product product = iProductService.findById(idProduct);
        if (product.getAmount() < amount) {
            model.addAttribute("messageAmount", "Quá số lượng hàng hiện có !");
        } else if (amount < 0) {
            model.addAttribute("messageAmount", "Giá trị không hợp lệ");
        } else {
            cart.changeAmount(idProduct, amount);
        }
        return showCart(cart, model);
    }

    @GetMapping("/pay")
    public String pay(@SessionAttribute(value = "cart", required = false) Cart cart) {
        Map<Product, Integer> productIntegerMap = cart.getAll();
        iProductService.pay(productIntegerMap);
        return "redirect:/products";
    }
}
