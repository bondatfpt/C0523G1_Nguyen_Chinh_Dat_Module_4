package com.example.cs4.customer.controller;

import com.example.cs4.customer.dto.CustomerDto;
import com.example.cs4.customer.model.Customer;
import com.example.cs4.customer.service.ICustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private ICustomerService iCustomerService;

    @GetMapping("/")
    public String showLList(@RequestParam(defaultValue = "0", required = false) int page,
                           @RequestParam(defaultValue = "", required = false) String searchName,
                           @RequestParam(defaultValue = "5", required = false) int size,
                           Model model) {
        Pageable pageable = PageRequest.of(page, size,Sort.by("id").descending());
        Page<Customer> customers = iCustomerService.showList(pageable, searchName);


        model.addAttribute("list", customers);
        model.addAttribute("searchName", searchName);
        return "/customer/showList";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam int id, RedirectAttributes redirectAttributes) {
        iCustomerService.delete(id);
        redirectAttributes.addFlashAttribute("success", "xoá thành công");
        return "redirect:/customers/";
    }

    @GetMapping("/showFormEdit")
    public String showFormEdit(@RequestParam int id, Model model) {
        Customer customer = iCustomerService.findById(id);
        CustomerDto customerDto = new CustomerDto();

        BeanUtils.copyProperties(customer, customerDto);
        String dateString = customerDto.getBirthDay();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(dateString, formatter);
        customerDto.setBirthDay(dateString);
        model.addAttribute("customerDto", customerDto);
//        model.addAttribute("customer", new CustomerDto());
        return "customer/edit";
    }

    @PostMapping("/edit")
    public String edit(@Valid @ModelAttribute("customerDto") CustomerDto customerDto,
                       BindingResult bindingResult,
                       Model model,
                       RedirectAttributes redirectAttributes) {
        new CustomerDto().validate(customerDto, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("customerDto", customerDto);
            redirectAttributes.addFlashAttribute("fail", "sửa thất bại");
            return "customer/edit";
        }
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDto,customer);
        String dateString = customer.getBirthDay();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(dateString, formatter);
        customer.setBirthDay(dateString);
        iCustomerService.save(customer);
        redirectAttributes.addFlashAttribute("success", "Sửa thành công");
        return "redirect:/customers/";
    }

    @GetMapping("/showViewDetail")
    public String showView(@RequestParam int id, Model model) {
        Customer customer = iCustomerService.findById(id);
        model.addAttribute("customer", customer);
        return "customer/backUp";
    }
}
