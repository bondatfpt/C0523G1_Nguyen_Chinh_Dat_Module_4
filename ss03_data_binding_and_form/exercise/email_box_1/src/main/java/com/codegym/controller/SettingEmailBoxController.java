package com.codegym.controller;

import com.codegym.model.SettingEmailBox;
import com.codegym.service.ISettingEmailBoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class SettingEmailBoxController {
    @Autowired
    private ISettingEmailBoxService iSettingEmailBoxService;

    @RequestMapping("")
    public String showListSetting(Model model) {
        model.addAttribute("settingEmailBoxList", iSettingEmailBoxService.getAll());
        return "index";
    }

    @GetMapping("/showFormUpdate")
    public String showFormUpdate(@RequestParam int id, Model model) {
        SettingEmailBox settingEmailBox = iSettingEmailBoxService.getSettingById(id);
        model.addAttribute("settingEmailBox",settingEmailBox);
        System.out.println(settingEmailBox);
        return "form-update";
    }

    @PostMapping("/update")
    public String update (@ModelAttribute SettingEmailBox settingEmailBox,Model model, @RequestParam int id){
        settingEmailBox.setId(id);
        System.out.println(settingEmailBox);
        iSettingEmailBoxService.update(settingEmailBox);
        showListSetting(model);
        return "index";
    }

    @ModelAttribute("languageList")
    public List<String> showListLanguages() {
        List<String> languageList = iSettingEmailBoxService.getLanguageList();
        return languageList;
    }

    @ModelAttribute("pageList")
    public int[] showPageList() {
        int[] pageList = iSettingEmailBoxService.getListPages();
        return pageList;
    }
}
