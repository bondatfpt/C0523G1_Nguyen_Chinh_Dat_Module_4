package com.example.cs4.customer.dto;


import com.example.cs4.account.model.Account;
import com.example.cs4.booking.model.Booking;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.Period;
import java.util.Set;

public class CustomerDto implements Validator {
    private int id;

//    @Pattern(regexp = "^[A-Z][a-zA-Z]*(\\s[A-Z][a-zA-Z]*)?$", message = "Tên phải chứa chữ cái đầu viết hoa")
    @NotBlank(message = "Tên không được để trống")
    private String name;

    @NotBlank(message = "Ngày sinh không được để trống")
    private String birthDay;

    @Pattern(regexp = "^\\d{9,12}$", message = "Số điện thoại phải có từ 9 đến 12 chữ số")
    private String phoneNumber;

    @Email(message = "Email không hợp lệ")
    @NotBlank(message = "Email không được để trống")
    private String email;

    @NotBlank(message = "Địa chỉ không được để trống")
    private String address;
    private boolean isDelete;
    private Account account;
    private Set<Booking> bookingSet;
    public CustomerDto() {
    }

    public CustomerDto(int id, String name, String birthDay, String phoneNumber, String email, String address) {
        this.id = id;
        this.name = name;
        this.birthDay = birthDay;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
    }

    public CustomerDto(int id, String name, String birthDay, String phoneNumber, String email, String address, boolean isDelete, Account account, Set<Booking> bookingSet) {
        this.id = id;
        this.name = name;
        this.birthDay = birthDay;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.isDelete = isDelete;
        this.account = account;
        this.bookingSet = bookingSet;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
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
    // Các getters và setters

    @Override
    public boolean supports(Class<?> clazz) {
        return CustomerDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CustomerDto customerDto = (CustomerDto) target;

        // Kiểm tra ngày sinh
        LocalDate birthDate = LocalDate.parse(customerDto.getBirthDay());
        LocalDate currentDate = LocalDate.now();
        int age = Period.between(birthDate, currentDate).getYears();
        if (age < 16) {
            errors.rejectValue("birthDay", "customerDto.birthDay.age", "Phải từ 16 tuổi trở lên");
        }

        // Kiểm tra số điện thoại
        String phoneNumber = customerDto.getPhoneNumber();
        if (phoneNumber != null && !phoneNumber.matches("^\\d{9,12}$")) {
            errors.rejectValue("phoneNumber", "customerDto.phoneNumber.invalid", "Số điện thoại không hợp lệ");
        }
    }
}
