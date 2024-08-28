package com.example.freshtime.domain;

public class Food {
    private String name;
    private String date;
    private Integer quantity;

    public Food(String name, String date, Integer quantity) {
        this.name = name;
        this.date = date;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
    