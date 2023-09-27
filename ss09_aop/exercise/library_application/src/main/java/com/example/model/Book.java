package com.example.model;

import org.hibernate.annotations.Check;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(columnDefinition = "varchar(100) not null ")
    private String name;
    @Column(columnDefinition = "int not null" )
    @Check(constraints = "amount >= 0")
    private int amount;
    @OneToMany(mappedBy = "book")
    private Set<OrderBook> orderSet;

    public Book() {
    }

    public Book(int id, String name, int amount, Set<OrderBook> orderSet) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.orderSet = orderSet;
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

    public Set<OrderBook> getOrderSet() {
        return orderSet;
    }

    public void setOrderSet(Set<OrderBook> orderSet) {
        this.orderSet = orderSet;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                ", orderSet=" + orderSet +
                '}';
    }
}
