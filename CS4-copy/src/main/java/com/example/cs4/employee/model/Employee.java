package com.example.cs4.employee.model;



import com.example.cs4.account.model.Account;
import com.example.cs4.booking.model.Booking;
import com.example.cs4.role.model.Role;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String phoneNumber;
    private String email;
    private String identityNumber;
    @Column(name = "is_deleted",columnDefinition = "boolean default false")
    private boolean isDeleted;
    @OneToOne
    @JoinColumn(name = "accountId", referencedColumnName = "accountId")
    private Account account;
    @OneToMany(mappedBy = "employee")
    private Set<Booking> bookingSet;



    public Employee() {
    }

    public Employee(int id, String name, String phoneNumber, String email, String identityNumber, boolean isDeleted) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.identityNumber = identityNumber;
        this.isDeleted = isDeleted;
    }

    public Employee(int id, String name, String phoneNumber, String email, String identityNumber, boolean isDeleted, Account account, Set<Booking> bookingSet) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.identityNumber = identityNumber;
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

    public String getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber;
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
}
