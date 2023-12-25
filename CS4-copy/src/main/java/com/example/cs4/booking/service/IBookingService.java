package com.example.cs4.booking.service;


import com.example.cs4.booking.model.Booking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.text.ParseException;

public interface IBookingService {
    Page<Booking> findAll(Pageable pageable, String phoneNumber);

    boolean check(Booking booking);

    boolean add(Booking booking);

    Booking findById(int id);

    boolean update(Booking booking);

    boolean delete(int id);

    void sendEmail(Booking booking);
    Page<Booking> findAllByPhoneAndBookingDate(Pageable pageable, String phoneNumber, String bookingDate) throws ParseException;
}
