package com.example.blog_application.model;

import javax.persistence.*;

@Entity
public class Blog {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    private String tittle;
    @Column(columnDefinition = "text")
    private String content;
    @Column(columnDefinition = "date")
    private String dateOfWriting;
    @ManyToOne
    @JoinColumn(name = "category_id",referencedColumnName = "id")
    private Category category;
    private String categoryName;

    public Blog() {}

    public Blog(int id, String tittle, String content, String dateOfWriting) {
        this.id = id;
        this.tittle = tittle;
        this.content = content;
        this.dateOfWriting = dateOfWriting;
    }

    public Blog(int id, String tittle, String content, String dateOfWriting, String categoryName) {
        this.id = id;
        this.tittle = tittle;
        this.content = content;
        this.dateOfWriting = dateOfWriting;
        this.categoryName = categoryName;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDateOfWriting() {
        return dateOfWriting;
    }

    public void setDateOfWriting(String dateOfWriting) {
        this.dateOfWriting = dateOfWriting;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", tittle='" + tittle + '\'' +
                ", content='" + content + '\'' +
                ", dateOfWriting='" + dateOfWriting + '\'' +
                '}';
    }
}
