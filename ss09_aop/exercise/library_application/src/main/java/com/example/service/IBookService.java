package com.example.service;

import com.example.model.Book;

import java.util.List;

public interface IBookService {
    List<Book> findAll ();

    Book findById (int id);

    void reduceTheNumber (int amount,int id);

}
