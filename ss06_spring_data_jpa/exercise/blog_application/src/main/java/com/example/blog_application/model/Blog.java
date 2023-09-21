package com.example.blog_application.model;

import javax.persistence.*;

@Entity
public class Blog {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String tittle;
    @Column(columnDefinition = "text")
    private String content;
    @Column(columnDefinition = "date")
    private String dateOfWriting;

    public Blog() {}

    public Blog(int id,String name, String tittle, String content, String dateOfWriting) {
        this.id = id;
        this.name = name;
        this.tittle = tittle;
        this.content = content;
        this.dateOfWriting = dateOfWriting;
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
                ", name='" + name + '\'' +
                ", tittle='" + tittle + '\'' +
                ", content='" + content + '\'' +
                ", dateOfWriting='" + dateOfWriting + '\'' +
                '}';
    }
}
