package com.example.cs4.booking.dto;



import com.example.cs4.customer.model.Customer;
import com.example.cs4.time.model.Time;
import com.example.cs4.yard.model.Yard;

import javax.validation.constraints.NotEmpty;

public class BookingDto {
    private int id;
    @NotEmpty(message = "Không được để trống!")
    private String bookingDate;
    private double deposit;
    private Yard yard;
    private Customer customer;
    private Time time;
    public BookingDto() {
    }

    public BookingDto(int id, String bookingDate, double deposit, Yard yard, Customer customer, Time time) {
        this.id = id;
        this.bookingDate = bookingDate;
        this.deposit = deposit;
        this.yard = yard;
        this.customer = customer;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public double getDeposit() {
        return deposit;
    }

    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }

    public Yard getYard() {
        return yard;
    }

    public void setYard(Yard yard) {
        this.yard = yard;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
}
