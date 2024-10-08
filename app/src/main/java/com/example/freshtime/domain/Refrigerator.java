package com.example.freshtime.domain;

import java.util.ArrayList;
import java.util.List;

public class Refrigerator {
    private Long id;
    private String name;
    private List<Food> foods = new ArrayList<>();

    public Refrigerator(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }

    // 냉장고에 음식 저장하기
    public void addFood(Food food) {
        foods.add(food);
    }
}