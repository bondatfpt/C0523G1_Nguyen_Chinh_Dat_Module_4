package com.example.cs4;

import com.example.cs4.account.model.Account;
import com.example.cs4.account.service.IAccountService;
import com.example.cs4.account.utils.EncryptedPasswordUtils;
import com.example.cs4.account.utils.RandomNumber;
import com.example.cs4.account.utils.WebUtils;
import com.example.cs4.customer.model.Customer;
import com.example.cs4.customer.service.ICustomerService;
import com.example.cs4.role.model.Role;
import com.example.cs4.role.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

@Controller
public class MainPageController {
    @Autowired
    private IAccountService iAccountService;
    @Autowired
    private IRoleService iRoleService;
    @Autowired
    private ICustomerService iCustomerService;
    private static String randomNumberStatic = RandomNumber.generateRandomString();
    private static String randomNumberGetAccount = RandomNumber.generateRandomString();

    @RequestMapping("")
    public String showHome() {
        return "/index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        System.out.println("Login run");
        return "account/login-page";
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied(Model model, Principal principal) {

        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();

            String userInfo = WebUtils.toString(loginedUser);

            model.addAttribute("userInfo", userInfo);

            String message = "Hi " + principal.getName() //
                    + "<br> You do not have permission to access this page!";
            model.addAttribute("message", message);

        }

        return "404Page";
    }


    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signUp(Model model) {
        System.out.println("Sign up run");
        model.addAttribute("accountCreate", new Account());
        model.addAttribute("customerCreate", new Customer());
        return "account/sign-up-page";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String save(@ModelAttribute("accountCreate") Account account,
                       @ModelAttribute("customerCreate") Customer customer, Model model) {
        if (iCustomerService.findCustomerByEmail(customer.getEmail()) != null) {
            model.addAttribute("emailError", "Email đã tồn tại!");
            return "account/sign-up-page";
        } else if (iCustomerService.findCustomerByPhoneNumber(customer.getPhoneNumber()) != null) {
            model.addAttribute("phoneNumberError", "Số điện thoại đã tồn tại!");
            return "account/sign-up-page";
        } else if (iAccountService.findAccountByUserName(account.getUserName()) != null) {
            model.addAttribute("userNameError", "Tên tài khoản đã tồn tại!");
            return "account/sign-up-page";
        } else {
            Role defaultRole = iRoleService.findRoleByRoleName("ROLE_USER");
            if (defaultRole == null) {
                defaultRole = new Role("ROLE_USER");
                iRoleService.save(defaultRole);
            }
            Set<Role> roles = new HashSet<>();
            roles.add(defaultRole);
            account.setRoleSet(roles);
            account.setPassword(EncryptedPasswordUtils.encryptedPassword(account.getPassword()));
            String activeCode = RandomNumber.generateRandomString();
            account.setActiveCode(activeCode);
            iAccountService.save(account);
            customer.setAccount(account);
            iCustomerService.save(customer);
            iAccountService.sendEmail(customer.getEmail(), account.getActiveCode());
            model.addAttribute("successSignup", true);
            return "account/sign-up-page";
        }
    }

    @RequestMapping(value = "/accountInfo", method = RequestMethod.GET)
    public String userInfo(Model model, Principal principal) {
        System.out.println("userInfo run");
        String userName = principal.getName();
        System.out.println(userName);
        Customer customer = iCustomerService.findCustomerByUsername(userName);
        model.addAttribute("username", userName);
        model.addAttribute("customerInfo", customer);
        System.out.println(customer);
        return "account/account-information-page";
    }

    @GetMapping("/showFormUpdate/{id}")
    public String showFormUpdate(@PathVariable Integer id, Model model) {
        Customer customer = iCustomerService.findById(id);
        model.addAttribute("customerUpdate", customer);
        return "account/update-account-page";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("customerUpdate") Customer customer, Model model) {
        iCustomerService.save(customer);
        model.addAttribute("customerInfo", customer);
        model.addAttribute("successUpdate", true);
        return "account/update-account-page";
    }

    @GetMapping("/showFormChangePassword/{id}")
    public String showFormChangePassword(@PathVariable Integer id, Model model) {
        model.addAttribute("idAccountChange", id);
        return "account/change-password-page";
    }

    @PostMapping("/changePassword")
    public String changePassword(@RequestParam("idAccountChange") int id,
                                 @RequestParam("oldPassword") String oldPass,
                                 @RequestParam("password") String newPass, Model model, Principal principal) {
        Account account = iAccountService.findAccountById(id);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(account);
        System.out.println("OLD PASS: " + oldPass);
        System.out.println("NEW PASS: " + newPass);
        System.out.println("PASS DATA: " + account.getPassword());
        if (passwordEncoder.matches(oldPass, account.getPassword())) {
            account.setPassword(EncryptedPasswordUtils.encryptedPassword(newPass));
            iAccountService.save(account);
            model.addAttribute("successChangePassword", true);
            return "account/change-password-page";
        } else {
            model.addAttribute("errorChangePassword", "Mật khẩu không đúng!");
            return "account/change-password-page";
        }
    }

    @PostMapping("/deleteAccount")
    public String delete(@RequestParam Integer idDeleted, Model model) {
        System.out.println("delete run");
        iAccountService.softDeleteAccount(idDeleted);
        return loginPage();
    }

    @GetMapping("showFormActiveAccount")
    public String showFormActiveAccount() {
        return "account/active-account-page";
    }

    @PostMapping("/activeAccount")
    public String activeAccount(@RequestParam(value = "userName", required = false) String userName,
                                Model model,
                                @RequestParam(value = "randomNumber") String randomNumber) {
        Account account = iAccountService.findAccountByUserName(userName);
        System.out.println("Account find by usser name: " + account);
        if (account != null && randomNumber.equals(account.getActiveCode())) {
            account.setActive(true);
            String activeCode = RandomNumber.generateRandomString();
            account.setActiveCode(activeCode);
            iAccountService.save(account);
            Customer customer = iCustomerService.findCustomerByAccount_AccountId(account.getAccountId());
            customer.setActive(true);
            iCustomerService.save(customer);
            model.addAttribute("successActive", true);
            return "account/active-account-page";
        } else if (account == null) {
            model.addAttribute("errorAccount", "Sai tên tài khoản");
            return "account/active-account-page";
        } else {
            model.addAttribute("errorAccount", "Mã kích hoạt không đúng !");
            return "account/active-account-page";
        }
    }

    @GetMapping("/showFormSendEmail")
    public String showFormSendEmail() {
        return "account/search-account-page";
    }

    @PostMapping("/sendEmailGetAccount")
    public String sendEmail(@RequestParam String email, Model model) {
        Customer customer = iCustomerService.findCustomerByEmail(email);
        System.out.println("Customer find by email !" + customer);
        Account account = iAccountService.findAccountById(customer.getAccount().getAccountId());
        System.out.println(account);
        if (customer != null && account != null && customer.isActive() && account.isActive()) {
            iAccountService.sendEmailGetAccount(email, account.getActiveCode());
            System.out.println("Active code: "+account.getActiveCode());
            model.addAttribute("sentEmail", true);
            return "account/search-account-page";
        } else {
            model.addAttribute("errorEmail", "Email không tồn tại trong hệ thống!");
            return "account/search-account-page";
        }
    }

    @GetMapping("/showFormGetAccount")
    public String getAccount() {
        return "account/get-account-page";
    }

    @PostMapping("/getAccount")
    public String getAccount(@RequestParam String randomNumber,
                             @RequestParam String password,
                             @RequestParam String userName, Model model) {
        Account account = iAccountService.findAccountByUserName(userName);
        if (account != null && account.isActive() && randomNumber.equals(account.getActiveCode())) {
            account.setPassword(EncryptedPasswordUtils.encryptedPassword(password));
            String activeCode = RandomNumber.generateRandomString();
            account.setActiveCode(activeCode);
            iAccountService.save(account);
            model.addAttribute("successGetAccount", true);
            return "account/get-account-page";
        } else if (account == null) {
            model.addAttribute("errorGetAccountBack", "Tên tài khoản không đúng !");
            return "account/get-account-page";
        } else {
            model.addAttribute("errorGetAccountBack", "Mã lấy lại tài khoản không đúng !");
            return "account/get-account-page";
        }
    }

    @GetMapping("/gmail")
    public String redirectToGmail() {
        return "redirect:https://mail.google.com";
    }
}
