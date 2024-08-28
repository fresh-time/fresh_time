package com.example.freshtime;

public class FoodItem {
    private String name;
    private String kcal;
    private String expiryDate;
    private int imageResource;

    public FoodItem(String name, String kcal, String expiryDate, int imageResource) {
        this.name = name;
        this.kcal = kcal;
        this.expiryDate = expiryDate;
        this.imageResource = imageResource;
    }

    public String getName() {
        return name;
    }

    public String getKcal() {
        return kcal;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public int getImageResource() {
        return imageResource;
    }
}