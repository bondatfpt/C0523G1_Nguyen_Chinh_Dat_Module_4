package com.codegym.controller;

import com.codegym.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class DictionaryController {
    @Autowired
    private  DictionaryService dictionaryService;
    @GetMapping("/showFormDictionary")
    public String showFormDictionary(){
        return "dictionary-english-vietnamese";
    }
    @PostMapping ("/translate")
    public String translate (@RequestParam String word, Model model){
       String result = dictionaryService.lookUpDictionary(word);
       model.addAttribute("result",result);
        return "dictionary-english-vietnamese";
    }
}
