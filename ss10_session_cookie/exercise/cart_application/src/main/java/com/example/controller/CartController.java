package com.example.controller;

import com.example.dto.CartDto;
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
                           CartDto cartDto, Model model) {
        Map<Product, Integer> productIntegerMap = cartDto.getAll();
        model.addAttribute("listCart", productIntegerMap);
        if (productIntegerMap.isEmpty()) {
            model.addAttribute("message", "Giỏ hàng hiện đang trống !");
        }
        double totalPayment = cartDto.countTotalPayment();
        model.addAttribute("totalPayment", totalPayment);
        int quantity = cartDto.countQuantityProduct(productIntegerMap);
        model.addAttribute("quantity", quantity);
        return "list-cart";
    }

    @GetMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable Integer id, Model model, @SessionAttribute
            (value = "cart", required = false) CartDto cartDto) {
        cartDto.deleteProduct(id);
        return showCart(cartDto, model);
    }

    @GetMapping("/changeAmount/{idProduct}/{amount}")
    public String changeAmount(@SessionAttribute(value = "cart", required = false) CartDto cartDto, Model model,
                               @PathVariable Integer idProduct, @PathVariable Integer amount) {
        Product product = iProductService.findById(idProduct);
        if (product.getAmount() < amount) {
            model.addAttribute("messageAmount", "Quá số lượng hàng hiện có !");
        } else if (amount < 0) {
            model.addAttribute("messageAmount", "Giá trị không hợp lệ");
        } else {
            cartDto.changeAmount(idProduct, amount);
        }
        return showCart(cartDto, model);
    }

    @GetMapping("/pay")
    public String pay(@SessionAttribute(value = "cart", required = false) CartDto cartDto) {
        Map<Product, Integer> productIntegerMap = cartDto.getAll();
        iProductService.pay(productIntegerMap);
        return "redirect:/products";
    }
}
