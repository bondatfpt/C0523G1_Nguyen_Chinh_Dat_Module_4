package com.example.cs4.time.service;


import com.example.cs4.time.model.Time;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.text.ParseException;
import java.util.List;

public interface ITimeService {
    Page<Time> findAll(Pageable pageable);
    boolean add(Time time);
    boolean delete(int id);
    List<Time> findByDate(String dateSearch) throws ParseException;
}
