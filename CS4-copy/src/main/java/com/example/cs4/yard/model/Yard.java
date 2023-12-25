package com.example.cs4.yard.model;



import com.example.cs4.booking.model.Booking;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "yards")
public class Yard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String address;
    @Column(columnDefinition = "text")
    private String description;
    private double price;
    private String image;
    @Column(name = "is_deleted",columnDefinition = "boolean default false")
    private boolean isDeleted;
    @OneToMany(mappedBy = "yard")
    private Set<Booking> bookingSet;

    public Yard() {
    }

    public Yard(int id, String name, String address, String description, double price, String image, boolean isDeleted, Set<Booking> bookingSet) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.description = description;
        this.price = price;
        this.image = image;
        this.isDeleted = isDeleted;
        this.bookingSet = bookingSet;
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

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public Set<Booking> getBookingSet() {
        return bookingSet;
    }

    public void setBookingSet(Set<Booking> bookingSet) {
        this.bookingSet = bookingSet;
    }
}
