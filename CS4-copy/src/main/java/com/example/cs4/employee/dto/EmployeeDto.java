package com.example.cs4.employee.dto;

import com.example.cs4.account.model.Account;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class EmployeeDto implements Validator {
    private int id;
    @Pattern(regexp = "^[a-zA-Z0-9 ]*$", message = "không đúng định dạng")
    private String name;
    @NotBlank(message = "Tên không được để trống")
    private String phoneNumber;
    @Email(message = "không đúng định dạng email")
    private String email;
    @Pattern(regexp = "^\\d{9,12}$", message = "Căn ước hoặc chứng minh phải có từ 9 đến 12 chữ số")
    private String identityNumber;
    private boolean isDeleted;
    private Account account;

    public EmployeeDto() {
    }

    public EmployeeDto(int id, String name, String phoneNumber, String email, String identityNumber, boolean isDeleted, Account account) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.identityNumber = identityNumber;
        this.isDeleted = isDeleted;
        this.account = account;
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

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}
