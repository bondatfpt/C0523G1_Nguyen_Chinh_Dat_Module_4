package com.example.controller;

import com.example.dto.UserDto;
import com.example.model.User;
import com.example.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class UserController {
    @Autowired
    private  IUserService iUserService;
    @GetMapping("")
    public String signUp(Model model){
        model.addAttribute("userDto", new UserDto());
        return "form-sign-up";
    }
    @PostMapping("/save")
    public String save (@Validated @ModelAttribute("userDto") UserDto userDto, BindingResult bindingResult){
        if(bindingResult.hasFieldErrors()){
            return "form-sign-up";
        }else {
            User user = new User();
            BeanUtils.copyProperties(userDto,user);
            iUserService.save(user);
            return "form-success";
        }
    }
}
