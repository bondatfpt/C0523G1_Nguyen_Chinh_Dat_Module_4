package com.example.controller;

import com.example.utils.WebUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
public class MainPageController {
    @RequestMapping(value = { "/", "/welcome" }, method = RequestMethod.GET)
    public String welcomePage(Model model) {
        System.out.println("welcome-page run");
        model.addAttribute("title", "Welcome");
        model.addAttribute("message", "This is welcome page!");
        return "/welcome-page";
    }

    //Đây là trang Admin
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPage(Model model, Authentication authentication) {
        System.out.println("Admin run");

        User loginedUser = (User)authentication.getPrincipal();

        String userInfo = WebUtils.toString(loginedUser);
        System.out.println("User obj"+loginedUser);
        System.out.println("User info"+userInfo);
        model.addAttribute("userInfo", userInfo);
        return "/admin-page";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model) {
        System.out.println("Login run");
        return "/login-page";
    }

    // khi người dùng logout khỏi hệ thống
    @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
    public String logoutSuccessfulPage(Model model) {
        System.out.println("Logout run");
        model.addAttribute("title", "Logout");
        return "/logout-successful-page";
    }

   //  khi người dùng đăng nhập thành công
    @PostMapping(value = "/userInfo")
    public String userInfo(Model model, Principal principal) {
        System.out.println("userInfo run");

        // Sau khi user login thanh cong se co principal
        String userName = principal.getName();
        System.out.println("Tên là: "+userName);

        System.out.println("User Name: " + userName);

        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);

        return "/user-info-page";
    }

    // khi người dùng là user mà thâm nhập trang admin thì mình vào đây
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied(Model model, Principal principal) {
        System.out.println("Denied run");
        if (principal != null) {
            System.out.println("Principal null");
            User loginedUser = (User) ((Authentication) principal).getPrincipal();

            String userInfo = WebUtils.toString(loginedUser);
            System.out.println(userInfo);

            model.addAttribute("userInfo", userInfo);

            String message = "Hi " + principal.getName() //
                    + "<br> You do not have permission to access this page!";
            model.addAttribute("message", message);

        }
        return "/403-page";
    }
}
