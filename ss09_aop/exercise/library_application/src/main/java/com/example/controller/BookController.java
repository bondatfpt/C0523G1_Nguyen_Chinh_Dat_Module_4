package com.example.controller;

import com.example.model.Book;
import com.example.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BookController {
    @Autowired
    private IBookService iBookService;

    @GetMapping("")
    public String showList(Model model){
        List<Book> bookList = iBookService.findAll();
        model.addAttribute("list",bookList);
        return "list";
    }

    @GetMapping("showFormDetail")
    public String showFormDetail (@RequestParam int id, Model model){
        Book book = iBookService.findById(id);
        model.addAttribute("bookDetails",book);
        return "form-details";
    }
}
