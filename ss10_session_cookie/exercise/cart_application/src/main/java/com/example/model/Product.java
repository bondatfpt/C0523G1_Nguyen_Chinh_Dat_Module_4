package com.example.model;

import org.hibernate.annotations.Check;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(columnDefinition = "varchar(100) not null")
    private String name;
    @Column(columnDefinition = "int not null")
    @Check(constraints = "amount >= 0")
    private int amount;
    @Column(columnDefinition = "double not null")
    private double price;
    @Column(columnDefinition = "text not null")
    private String description;
    @Column(columnDefinition = "varchar(255) not null")
    private String imagePath;

    public Product() {
    }

    public Product(String name, int amount, double price, String description, String imagePath) {
        this.name = name;
        this.amount = amount;
        this.price = price;
        this.description = description;
        this.imagePath = imagePath;
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Product other = (Product) obj;
        return Objects.equals(this.id, other.id);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
