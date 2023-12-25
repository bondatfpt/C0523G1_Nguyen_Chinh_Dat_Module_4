package com.example.dto;

import com.example.model.Category;
import com.sun.istack.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

public class PostDto implements Validator {
    private Integer id;
    @NotBlank(message = "Không được để trống !")
    private String title;
    @NotBlank(message = "Không được để trống !")

    private String content;
    @NotBlank(message = "Không được để trống !")

    private String datePost;
    @NotBlank(message = "Không được để trống !")

    private String author;
    private Category category;

    public PostDto() {
    }

    public PostDto(String title, String content, String datePost, String author, Category category) {
        this.title = title;
        this.content = content;
        this.datePost = datePost;
        this.author = author;
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDatePost() {
        return datePost;
    }

    public void setDatePost(String datePost) {
        this.datePost = datePost;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "PostDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", datePost='" + datePost + '\'' +
                ", author='" + author + '\'' +
                ", category=" + category +
                '}';
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        LocalDate localDate = LocalDate.parse(datePost);
        LocalDate now = LocalDate.now();
        if (localDate.isBefore(now)){
            errors.getFieldError("datePost");
            System.out.println("Đã tới đây");
        }
    }
}
