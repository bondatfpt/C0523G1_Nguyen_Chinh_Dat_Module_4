package com.codegym.controller;

import com.codegym.model.Sandwich;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class SandwichController {
    @GetMapping("/showFormCondiments")
    public String showFormCondiments (){
        return "form-sandwich-condiments";
    }
    @PostMapping("/listCondiments")
    public String chooseCondiments (@RequestParam(required = false) String lettuce,
                                    @RequestParam(required = false) String tomato,
                                    @RequestParam(required = false) String mustard,
                                    @RequestParam (required = false) String sprouts,
                                    Model model){
        List<String> stringList = new ArrayList<>();
        String [] condiments = {lettuce,tomato,mustard,sprouts};
        for (int i = 0; i < condiments.length; i++) {
            if (condiments[i] != null){
                stringList.add(condiments[i]);
            }
        }
        if(stringList.isEmpty()){
            model.addAttribute("condiments","Normal Sandwich");
        }
        Sandwich sandwich = new Sandwich(stringList);
        model.addAttribute("condiments", sandwich);
        return "form-sandwich-condiments";
    }
}
