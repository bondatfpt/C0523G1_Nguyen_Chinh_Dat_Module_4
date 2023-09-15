package com.codegym.model;

import java.util.Arrays;
import java.util.List;

public class Sandwich {
    private int id;
    private List<String>condiments;

    public Sandwich(List<String> condiments) {
        this.condiments = condiments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Sandwich - " +
                "condiments: " + condiments;
    }
}
