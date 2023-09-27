package com.example.service;

import com.example.model.Book;
import com.example.repository.IBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService implements IBookService{
    @Autowired
    private IBookRepository iBookRepository;
    @Override
    public List<Book> findAll() {
        return (List<Book>) iBookRepository.findAll();
    }

    @Override
    public Book findById(int id) {
        return iBookRepository.findById(id).get();
    }

    @Override
    public void reduceTheNumber(int amount,int id) {
        iBookRepository.reduceTheNumber(amount,id);
    }
}
