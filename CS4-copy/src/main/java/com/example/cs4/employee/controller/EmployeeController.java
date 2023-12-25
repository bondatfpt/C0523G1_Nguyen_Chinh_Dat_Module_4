package com.example.cs4.employee.controller;

import com.example.cs4.account.service.IAccountService;
import com.example.cs4.employee.dto.EmployeeDto;
import com.example.cs4.employee.model.Employee;
import com.example.cs4.employee.service.IEmployeeService;
import com.example.cs4.role.service.IRoleService;
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

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private IEmployeeService iEmployeeService;
    @Autowired
    private IAccountService iAccountService;
    @Autowired
    private IRoleService iRoleService;

    @GetMapping("")
    public String showList(@RequestParam(defaultValue = "0", required = false) int page,
                           @RequestParam(defaultValue = "", required = false) String searchName,
                           @RequestParam(defaultValue = "5", required = false) int size,
                           Model model) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        Page<Employee> list = iEmployeeService.showList(pageable, searchName);
        model.addAttribute("list", list);
        return "/employee/showList";
    }

    @GetMapping("/showFormCreate")
    public String showFormCreate(Model model) {
        model.addAttribute("employeeDto", new EmployeeDto());
        return "/employee/create";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("employeeDto") EmployeeDto employeeDto,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes,
                         Model model) {
        new EmployeeDto().validate(employeeDto,bindingResult);
        if (bindingResult.hasErrors()){
            model.addAttribute("employeeDto", employeeDto);
            redirectAttributes.addFlashAttribute("fail","thêm thất bại");
            return "/employee/create";
        }
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDto,employee);
        iEmployeeService.save(employee);

        return "redirect:/employee";
    }
    @GetMapping("/delete")
    public String delete(@RequestParam int id, RedirectAttributes redirectAttributes){
        iEmployeeService.delete(id);
        redirectAttributes.addFlashAttribute("success","Xoá thành công");
        return "redirect:/employee";
    }
    @GetMapping("/showEdit")
    public String showFormEdit(@RequestParam int id, Model model){
        Employee employee = iEmployeeService.findById(id);
        EmployeeDto employeeDto = new EmployeeDto();
        BeanUtils.copyProperties(employee,employeeDto);
        model.addAttribute("employeeDto",employeeDto);
        return "employee/edit";
    }
    @PostMapping("/edit")
    public String edit(@Valid @ModelAttribute("employeeDto") EmployeeDto employeeDto,
                       BindingResult bindingResult,
                       Model model,
                       RedirectAttributes redirectAttributes
                       ){
        new EmployeeDto().validate(employeeDto,bindingResult);
        if (bindingResult.hasErrors()){
            model.addAttribute("employeeDto",employeeDto);
            redirectAttributes.addFlashAttribute("fail", "Không đúng dịnh dạng");
        }
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDto,employee);
        iEmployeeService.save(employee);
        redirectAttributes.addFlashAttribute("success", "chỉnh sửa thành công");
        return "redirect:/employee";
    }
    @GetMapping("showView")
    public String detail(@RequestParam int id, Model model){
        model.addAttribute("employee", iEmployeeService.findById(id));
        return "employee/detail";
    }
}
