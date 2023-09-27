package com.example.controller;

import com.example.model.Book;
import com.example.model.OrderBook;
import com.example.service.IBookService;
import com.example.service.IOrderService;
import com.example.ultils.GenerateRandomNumbers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class OrderController {
    @Autowired
    private IOrderService iOrderService;
    @Autowired
    private IBookService iBookService;

    @PostMapping("/save")
    public String saveOrder(@RequestParam int id, Model model) {
        GenerateRandomNumbers generateRandomNumbers = new GenerateRandomNumbers();
        Book book = iBookService.findById(id);
        boolean flag = true;
        if (book.getAmount() <= 0) {
            model.addAttribute("message", "Out of books");
            model.addAttribute("bookDetails", book);
            flag = false;
            model.addAttribute("flag",flag);
            return "form-details";
        } else {
            model.addAttribute("flag",flag);
            String randomNumber = generateRandomNumbers.generateRandomNumbers();
            model.addAttribute("bookLoanCode", randomNumber);
            LocalDate localDate = LocalDate.now();
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE;
            String borrowedDay = localDate.format(dateTimeFormatter);
            OrderBook orderBook = new OrderBook(book.getName(), borrowedDay, randomNumber, book);
            iOrderService.save(orderBook);
            int amount = book.getAmount() - 1;
            iBookService.reduceTheNumber(amount, id);
            return "redirect:/";
        }
    }

    @GetMapping("/showListBorrowed")
    public String showListBorrowed (Model model){
        List<OrderBook> orderBookList = iOrderService.findAll();
        model.addAttribute("borrowedList",orderBookList);
        return "list-of-borrowed-books";
    }

    @PostMapping ("giveBookBack")
    public String giveBookBack (@RequestParam String bookLoanCode,@RequestParam int idOrder,@RequestParam int idBook,Model model){
        OrderBook orderBook = iOrderService.findById(idOrder);
        if (bookLoanCode.trim().equals(orderBook.getBookLoanCode())){
            iOrderService.deleteOrder(idOrder);
            Book book = iBookService.findById(idBook);
            if (book != null){
                int amount = book.getAmount() + 1;
                iBookService.reduceTheNumber(amount,book.getId());
            }
            return "redirect:/";
        }else {
            model.addAttribute("messageError", "Wrong Book Loan Code");
            return showListBorrowed(model);
        }
    }
}
