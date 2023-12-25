package com.example.cs4.time.controller;


import com.example.cs4.time.dto.TimeDto;
import com.example.cs4.time.model.Time;
import com.example.cs4.time.service.ITimeService;
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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("times")
public class TimeController {
    @Autowired
    private ITimeService timeService;

    @GetMapping("")
    public ModelAndView showAll(@RequestParam(defaultValue = "0", required = false) int page) {
        Pageable pageable = PageRequest.of(page, 5, Sort.by("time"));
        Page<Time> timePage = timeService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("time/list", "timePage", timePage);
        return modelAndView;
    }

    @GetMapping("create")
    public String showFormCreate(Model model) {
        model.addAttribute("timeDto", new TimeDto());
        return "time/create";
    }

    @PostMapping("create")
    public String create(@Valid @ModelAttribute TimeDto timeDto,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()){
            return "time/create";
        }
        Time time = new Time();
        BeanUtils.copyProperties(timeDto,time);
        try {
            timeService.add(time);
            redirectAttributes.addFlashAttribute("message", "Thêm thời gian thành công!");
        }catch (IllegalArgumentException ex) {
            redirectAttributes.addFlashAttribute("message", "Đã có khung giờ này!");
        }
        return "redirect:/times/create";
    }

    @PostMapping("delete")
    public String delete(@RequestParam String id, RedirectAttributes redirectAttributes) {
        try {
            timeService.delete(Integer.parseInt(id));
            redirectAttributes.addFlashAttribute("message", "Xóa thời gian thành công!");
        } catch (IllegalArgumentException ex) {
            redirectAttributes.addFlashAttribute("message", "Không có khung giờ này!");
        }
        return "redirect:/times";
    }
}
