package com.example.model;

import javax.persistence.*;

@Entity
@Table(name = "orders")
public class OrderBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(columnDefinition = "varchar(100) not null")
    private String nameOfBook;
    @Column(columnDefinition = "date not null")
    private String borrowedDay;

    @Column(columnDefinition = "varchar(10) not null")
    private String bookLoanCode;

    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private Book book;

    public OrderBook() {
    }

    public OrderBook(String nameOfBook, String borrowedDay, String bookLoanCode, Book book) {
        this.nameOfBook = nameOfBook;
        this.borrowedDay = borrowedDay;
        this.bookLoanCode = bookLoanCode;
        this.book = book;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getNameOfBook() {
        return nameOfBook;
    }

    public void setNameOfBook(String nameOfBook) {
        this.nameOfBook = nameOfBook;
    }

    public String getBorrowedDay() {
        return borrowedDay;
    }

    public void setBorrowedDay(String borrowedDay) {
        this.borrowedDay = borrowedDay;
    }

    public String getBookLoanCode() {
        return bookLoanCode;
    }

    public void setBookLoanCode(String bookLoanCode) {
        this.bookLoanCode = bookLoanCode;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "OrderBook{" +
                "id=" + id + ", nameOfBook='" + nameOfBook + '\'' +
                ", borrowedDay='" + borrowedDay + '\'' +
                ", bookLoanCode='" + bookLoanCode + '\'' +
                ", book=" + book +
                '}';
    }
}
