package com.example.cs4.booking.model;



import com.example.cs4.customer.model.Customer;
import com.example.cs4.employee.model.Employee;
import com.example.cs4.time.model.Time;
import com.example.cs4.yard.model.Yard;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(columnDefinition = "DATE")
    private String bookingDate;
    private double deposit;
    @Column(name = "is_deleted",columnDefinition = "boolean default false")
    private boolean isDeleted;
    @ManyToOne
    @JoinColumn(name = "yard_id", referencedColumnName = "id")
    private Yard yard;
    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee employee;
    @ManyToOne
    @JoinColumn(name = "time_id",referencedColumnName = "id")
    private Time time;

    public Booking() {
    }

    public Booking(int id, String bookingDate, double deposit, boolean isDeleted) {
        this.id = id;
        this.bookingDate = bookingDate;
        this.deposit = deposit;
        this.isDeleted = isDeleted;
    }

    public Booking(int id, String bookingDate, double deposit, boolean isDeleted, Yard yard, Customer customer, Employee employee, Time time) {
        this.id = id;
        this.bookingDate = bookingDate;
        this.deposit = deposit;
        this.isDeleted = isDeleted;
        this.yard = yard;
        this.customer = customer;
        this.employee = employee;
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

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
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

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return Objects.equals(bookingDate, booking.bookingDate) && Objects.equals(yard, booking.yard) && Objects.equals(time, booking.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookingDate, yard, time);
    }
}
