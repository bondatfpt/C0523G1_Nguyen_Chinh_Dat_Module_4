package com.codegym.controller;

import com.codegym.service.CalculateService;
import com.codegym.service.ICalculateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CalculatorController {
    @Autowired
    private ICalculateService iCalculateService;
    @GetMapping("")
    public String showFormCalculator() {
        return "index";
    }
    @PostMapping ("/calculate")
    public String calculate (@RequestParam double firstNum,
                             @RequestParam String operator,
                             @RequestParam double secondNum,
                             Model model){
        model.addAttribute("firstNum",firstNum);
        model.addAttribute("secondNum",secondNum);
        if(secondNum == 0 && ("/".equals(operator))){
            model.addAttribute("result","Can't divided zero!");
        } else {
            double result = iCalculateService.calculate(firstNum,operator,secondNum);
            model.addAttribute("result", result);
        }
        return "index";
    }
}
