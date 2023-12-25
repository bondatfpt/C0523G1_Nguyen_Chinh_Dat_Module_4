package com.example.cs4.time.service;


import com.example.cs4.time.model.Time;
import com.example.cs4.time.repository.ITimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class TimeService implements ITimeService {
    @Autowired
    private ITimeRepository timeRepository;


    @Override
    public Page<Time> findAll(Pageable pageable) {
        return timeRepository.findAllByDeleteIs(pageable);
    }


    @Override
    public boolean add(Time time) {
        if (timeRepository.getAll().contains(time)) {
            throw new IllegalArgumentException("Error!");
        }
        timeRepository.save(time);
        return true;
    }

    @Override
    public boolean delete(int id) {
        Time time = timeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Error!"));
        time.setDeleted(true);
        timeRepository.save(time);
        return true;
    }

    @Override
    public List<Time> findByDate(String dateSearch) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatter.parse(dateSearch);
        return timeRepository.findByDate(date);
    }
}
