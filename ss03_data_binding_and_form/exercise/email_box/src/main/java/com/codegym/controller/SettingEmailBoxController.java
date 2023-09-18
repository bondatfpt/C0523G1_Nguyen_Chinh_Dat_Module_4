package com.codegym.controller;

import com.codegym.model.SettingEmailBox;
import com.codegym.service.ISettingEmailBoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class SettingEmailBoxController {
    @Autowired
    private ISettingEmailBoxService iSettingEmailBoxService ;
    @RequestMapping("")
    public String showFormSetting(Model model) {
        model.addAttribute("settingEmailBox",iSettingEmailBoxService.getAll().get(0));
        return "index";
    }
    @ModelAttribute("languageList")
    public List<String> showListLanguages(){
        List<String> languageList = iSettingEmailBoxService.getLanguageList();
        return languageList;
    }
    @ModelAttribute("pageList")
    public int[] showPageList (){
        int [] pageList = iSettingEmailBoxService.getListPages();
        return pageList;
    }
    @GetMapping("/save")
    public String saveSetting ()
    @PostMapping ("/update")
    public String updateSetting (@ModelAttribute("settingEmailBox") SettingEmailBox settingEmailBox, Model model){
        model.addAttribute("languages",settingEmailBox.getLanguages());
        model.addAttribute("pages",settingEmailBox.getPages());
        model.addAttribute("spamsFilter",settingEmailBox.isSpamsFilter());
        model.addAttribute("signature",settingEmailBox.getSignature());
        return "index";
    }
}
