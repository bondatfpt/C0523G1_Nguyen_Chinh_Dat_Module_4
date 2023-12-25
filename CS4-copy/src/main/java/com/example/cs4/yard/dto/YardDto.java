package com.example.cs4.yard.dto;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class YardDto implements Validator {
    private int id;
    private String name;
    private String address;
    private String description;
    private double price;
    private String image;

    public YardDto(int id, String name, String address, String description, double price, String image) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.description = description;
        this.price = price;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public YardDto() {
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        YardDto yardDto = (YardDto) target;
        if(yardDto.getName().equals("")){
            errors.rejectValue("name",null,"*Không được để trống");
        } else if (yardDto.getName().length() >200) {
            errors.rejectValue("name",null,"*Không được vượt quá 200 ký tự");
        } else if (yardDto.getName().matches("^[A-Z][a-z]{1,199}( ?[A-z][a-z]{1,198})$")) {
            errors.rejectValue("name",null,"*Nhập sai định dạng, chữ cái đầu phải in hoa");
        } else if (yardDto.getAddress().equals("")) {
            errors.rejectValue("address",null,"*Không được để trống");
        } else if (yardDto.getAddress().length() > 300) {
            errors.rejectValue("address",null,"*Không được vượt quá 300 ký tự");
        } else if (yardDto.getDescription().equals("")) {
            errors.rejectValue("description",null,"*Không được để trống");
        } else if (yardDto.getDescription().length() > 300) {
            errors.rejectValue("description",null,"*Không được vượt quá 300 ký tự");
        }
        else if (yardDto.getPrice() <= 1) {
            errors.rejectValue("price", null, "*Vui lòng nhập số tiền");
        }else if (yardDto.getPrice()>999999999) {
            errors.rejectValue("price", null, "*Bạn đang nhập số tiền quá lớn");
        }else if (yardDto.getImage().equals("")) {
            errors.rejectValue("image",null,"*Không được để trống");
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}