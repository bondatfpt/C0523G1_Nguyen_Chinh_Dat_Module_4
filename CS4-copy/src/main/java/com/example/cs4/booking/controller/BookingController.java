package com.example.cs4.booking.controller;


import com.example.cs4.booking.dto.BookingDto;
import com.example.cs4.booking.model.Booking;
import com.example.cs4.booking.service.IBookingService;
import com.example.cs4.customer.model.Customer;
import com.example.cs4.customer.service.ICustomerService;
import com.example.cs4.employee.service.IEmployeeService;
import com.example.cs4.time.model.Time;
import com.example.cs4.time.service.ITimeService;
import com.example.cs4.yard.service.IYardService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("bookings")
public class BookingController {
    @Autowired
    private IBookingService bookingService;
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private IYardService yardService;
    @Autowired
    private ITimeService timeService;
    @Autowired
    private IEmployeeService employeeService;

    @GetMapping("")
    public String showAll(@RequestParam(defaultValue = "0", required = false) int page,
                          @RequestParam(defaultValue = "", required = false) String phoneNumber,
                          Principal principal,
                          Model model) {
        Pageable pageable = PageRequest.of(page, 4);
        Page<Booking> bookingPage = bookingService.findAll(pageable, phoneNumber);
        String userName = principal.getName();
        System.out.println(userName);
        Customer customer = customerService.findCustomerByUsername(userName);
        model.addAttribute("username", userName);
        model.addAttribute("customerInfo", customer);
        model.addAttribute("bookingPage", bookingPage);
        model.addAttribute("phoneNumber", phoneNumber);
        return "booking/list";
    }
    @GetMapping("findByDate")
    public String showAll(@RequestParam(defaultValue = "0", required = false) int page,
                          @RequestParam(defaultValue = "", required = false) String phoneNumber,
                          @RequestParam(defaultValue = "", required = false) String bookingDate,
                          Principal principal,
                          Model model) throws ParseException {
        Pageable pageable = PageRequest.of(page, 4);
        Page<Booking> bookingPage = bookingService.findAllByPhoneAndBookingDate(pageable, phoneNumber,bookingDate);
//        ModelAndView modelAndView = new ModelAndView("booking/list", "bookingPage", bookingPage);
//        modelAndView.addObject("phoneNumber", phoneNumber);
        String userName = principal.getName();
        System.out.println(userName);
        Customer customer = customerService.findCustomerByUsername(userName);
        model.addAttribute("username", userName);
        model.addAttribute("customerInfo", customer);
        model.addAttribute("bookingPage", bookingPage);
        model.addAttribute("phoneNumber", phoneNumber);
        model.addAttribute("bookingDate", bookingDate);
        return "booking/list";
    }
    @GetMapping("create/{userName}/{yardId}/{dateSearch}")
    public String showFormCreate(Model model,
                                 @PathVariable String userName,
                                 @PathVariable int yardId,
                                 @PathVariable String dateSearch) {
        BookingDto bookingDto = new BookingDto();
        bookingDto.setCustomer(customerService.findCustomerByUsername(userName));
        bookingDto.setYard(yardService.findById(yardId));
        bookingDto.setDeposit(yardService.findById(yardId).getPrice() * 0.5);
        model.addAttribute("customer", customerService.findCustomerByUsername(userName));
        model.addAttribute("yard", yardService.findById(yardId));
        try {
            model.addAttribute("timeList", timeService.findByDate(dateSearch));
            bookingDto.setBookingDate(dateSearch);
        } catch (ParseException e) {
            model.addAttribute("timeList", timeService.findAll(Pageable.unpaged()));
        }
        model.addAttribute("bookingDto", bookingDto);
        return "booking/create";
    }

    //    @PostMapping("create")
//    public String createBooking(@Valid @ModelAttribute BookingDto bookingDto,
//                                Model model,
//                                BindingResult bindingResult,
//                                @RequestParam List<Time> timeList,
//                                RedirectAttributes redirectAttributes) {
//        if (bindingResult.hasErrors()) {
//            return "booking/create";
//        }
//        Booking booking = new Booking();
//        BeanUtils.copyProperties(bookingDto, booking);
//        for(Time time : timeList){
//           booking.setTime(time);
//            booking.setEmployee(employeeService.findById(1));
//
//
//        try {
//            bookingService.check(booking);
//            if(booking.getTime().getTime() >= 16 && booking.getTime().getTime() <= 22){
//                booking.getYard().setPrice(booking.getYard().getPrice() * 1.2);
//            }
//            model.addAttribute("booking", booking);
//            return "booking/confirm";
//        } catch (IllegalArgumentException ex) {
//            redirectAttributes.addFlashAttribute("full", "Sân kín vào khung giờ này. Mời bạn chọn giờ khác!");
//        } catch (RuntimeException ex) {
//            redirectAttributes.addFlashAttribute("error", "Ngày quá khứ rồi!");
//        }
//        }
//        return "redirect:/bookings/create/" + booking.getCustomer().getAccount().getUserName() + "/" + booking.getYard().getId();
//    }
    @PostMapping("create")
    public String createBooking(@Valid @ModelAttribute BookingDto bookingDto,
                                Model model,
                                BindingResult bindingResult,
                                @RequestParam (required = false) List<Time> timeList,
                                RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors() || timeList == null) {
            model.addAttribute("error","Mời chọn giờ !");
            return "booking/create";
        }
        List<Booking> bookingList = new ArrayList<>();
//        Booking booking = new Booking();
//        BeanUtils.copyProperties(bookingDto, booking);
        int total = 0;
        for (Time time : timeList) {
            Booking booking = new Booking();
            BeanUtils.copyProperties(bookingDto, booking);
            booking.setTime(time);
            booking.setEmployee(employeeService.findById(1));
            if (booking.getTime().getTime() >= 16 && booking.getTime().getTime() <= 22) {
                booking.getYard().setPrice(booking.getYard().getPrice() * 1.2);
            }
            total += booking.getYard().getPrice();
            bookingList.add(booking);
        }
        model.addAttribute("bookingList", bookingList);
        model.addAttribute("booking", bookingList.get(0));
        model.addAttribute("timeList", timeList);
        model.addAttribute("total", total);
        return "booking/confirm";
    }


    @PostMapping("confirm")
    public String pay(@RequestParam List<Time> timeList,
                      @ModelAttribute Booking booking,
                      RedirectAttributes redirectAttributes) {
        List<Booking> bookingList = new ArrayList<>();
        for (Time time : timeList) {
            Booking booking1 = new Booking();
            BeanUtils.copyProperties(booking,booking1);

            booking1.setTime(time);
            bookingList.add(booking1);
        }
        for (Booking booking1 : bookingList) {
            bookingService.add(booking1);
        }
//            bookingService.add(booking);
        redirectAttributes.addFlashAttribute("message", "Bạn đã đặt sân thành công! Cảm ơn bạn đã sử dụng dịch vụ tại DATSAN. Kiểm tra email của bạn!");

        bookingService.sendEmail(bookingList.get(0));
        return "redirect:/yards";
    }

    @GetMapping("update/{id}/{userName}")
    public String showFormUpdate(Model model, @PathVariable int id, @PathVariable String userName) {
        BookingDto bookingDto = new BookingDto();
        Booking booking = bookingService.findById(id);
        BeanUtils.copyProperties(booking, bookingDto);
        model.addAttribute("bookingDto", bookingDto);
        model.addAttribute("timeList", timeService.findAll(Pageable.unpaged()));
        model.addAttribute("yardList", yardService.findAll());
        model.addAttribute("userName", userName);
        return "booking/update";
    }

    @PostMapping("update")
    public String update(@Valid @ModelAttribute BookingDto bookingDto,
                         @RequestParam(value = "userName", required = true, defaultValue = "admin") String userName,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "booking/update";
        }
        Booking booking = new Booking();
        BeanUtils.copyProperties(bookingDto, booking);
        booking.setEmployee(employeeService.findByUserName(userName));
        try {
            bookingService.update(booking);
            redirectAttributes.addFlashAttribute("message", "Bạn đã sửa thành công! Sân " + booking.getYard().getName() + " lúc " + booking.getTime().getTime() + "h, ngày " + booking.getBookingDate());
        } catch (IllegalArgumentException ex) {
            redirectAttributes.addFlashAttribute("full", "Sân kín vào khung giờ này. Mời bạn chọn giờ khác!");
        } catch (RuntimeException ex) {
            redirectAttributes.addFlashAttribute("error", "Ngày quá khứ rồi!");
        }
        return "redirect:/bookings/update/" + booking.getId() + "/" + booking.getEmployee().getAccount().getUserName();
    }
}