package com.example.dto;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.*;

public class UserDto implements Validator {
    private int id;
    @NotEmpty(message = "Họ không nên để trống !")
    @Size(min = 5,max = 45,message = "Độ dài tối thiểu 5 kí tự, nhiều nhất 45 kí tự!")
    @Pattern(regexp = "^[A-Za-z]+$", message = "Họ chỉ nên chứa các kí tự là chữ cái và viết không dấu!")
    private String firstName;
    @NotEmpty(message = "Tên không nên để trống !")
    @Size(min = 5,max = 45,message = "Độ dài tối thiểu 5 kí tự, nhiều nhất 45 kí tự!")
    @Pattern(regexp = "^[A-Za-z]+$", message = "Tên chỉ nên chứa các kí tự là chữ cái và viết không dấu!")
    private String lastName;
    @NotEmpty(message = "Tuổi không nên để trống!")
    @Min(value = 18,message = "Bạn phải đủ 18 tuổi mới có thể đăng ký!")
    private String age;
    @NotEmpty(message = "Số điện thoại không nên để trống!")
    @Pattern(regexp = "^[0-9]{10,}",message = "Số điện thoại bắt đầu bằng 1 chữ số và chỉ có kí tự số và có độ dài từ 10 số trở lên!")
    private String phoneNumber;
    @Email(message = "Email sai định dạng ! (VD: abc@xyz.com)")
    @NotEmpty(message = "Email không nên để trống!")
    private String email;

    public UserDto() {
    }

    public UserDto(int id, String firstName, String lastName, String age, String phoneNumber, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
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

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {}
}
