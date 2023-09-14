package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CustomerController {
    @Autowired
    private ICustomerService icustomerService;

    @GetMapping("/customer")
    public String showList(Model model){
        List<Customer> customerList = icustomerService.getAll();
        model.addAttribute("list",customerList);
        return "list";
    }

}
