package com.example.dto;


import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class SongDto implements Validator {
    private int id;
    @Size(max = 800,message = "Tên bài hát tối đa 800 kí tự và viết không dấu!")
    @Pattern(regexp = "^[A-Z0-9a-z\\s]+$", message = "Tên bài hát không được chứa kí tự đặc biệt!")
    private String name;
    @Size(max = 300, message = "Tên tác giả tối đa 300 kí tự và viết không dấu!")
    @Pattern(regexp = "^[A-Z0-9a-z\\s]+$", message = "Tên tác giả không được chứa kí tự đặc biệt!")
    private String author;
    @Size(max = 1000, message = "Thể loại nhạc tối đa 1000 kí tự và viết không dấu!")
    @Pattern(regexp = "^[A-Z0-9a-z,\\s]+$", message = "Thể loại nhạc có thể chứa dấu phẩy!")
    private String type;

    public SongDto() {
    }

    public SongDto(int id, String name, String author, String type) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.type = type;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

    }

    @Override
    public String toString() {
        return "SongDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
