package com.example.cs4.customer.model;



import com.example.cs4.account.model.Account;
import com.example.cs4.booking.model.Booking;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Column(name = "birthday")
    private String birthDay;
    private String phoneNumber;
    private String email;
    private String address;
    @Column(name = "is_deleted",columnDefinition = "boolean default false")
    private boolean isDeleted;
    @Column(name = "is_active",columnDefinition = "boolean default false")
    private boolean isActive;
    @OneToOne
    @JoinColumn(name = "accountId",referencedColumnName = "accountId")
    private Account account;
    @OneToMany(mappedBy = "customer")
    private Set<Booking> bookingSet;

    public Customer() {
    }

    public Customer(int id, String name, String birthDay, String phoneNumber, String email, String address, boolean isDeleted) {
        this.id = id;
        this.name = name;
        this.birthDay = birthDay;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.isDeleted = isDeleted;
    }

    public Customer(int id, String name, String birthDay, String phoneNumber, String email, String address, boolean isDeleted, Account account, Set<Booking> bookingSet) {
        this.id = id;
        this.name = name;
        this.birthDay = birthDay;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.isDeleted = isDeleted;
        this.account = account;
        this.bookingSet = bookingSet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Set<Booking> getBookingSet() {
        return bookingSet;
    }

    public void setBookingSet(Set<Booking> bookingSet) {
        this.bookingSet = bookingSet;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDay='" + birthDay + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", isDeleted=" + isDeleted +
                ", isActive=" + isActive +
                ", account=" + account +
                ", bookingSet=" + bookingSet +
                '}';
    }
}
