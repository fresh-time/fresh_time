package com.example.freshtime;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.freshtime.domain.Food;
import com.example.freshtime.domain.Refrigerator;
import com.example.freshtime.repository.RefrigeratorRepository;

import java.util.ArrayList;
import java.util.List;

public class MyRefFood extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FoodAdapter foodAdapter;
    private List<FoodItem> foodItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myreffood);

        // Get the data from the intent
        Intent intent = getIntent();
        String refName = intent.getStringExtra("refName");

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 데이터 초기화
        foodItemList = new ArrayList<>();
        Refrigerator refrigerator = RefrigeratorRepository.getRefrigeratorByName(refName);
        if (refrigerator != null) {
            for (Food food : refrigerator.getFoods()) {
                foodItemList.add(new FoodItem(
                        food.getName(),
                        "/88.7kcal",
                        food.getDate(),
                        R.drawable.banana
                ));
            }
        }

        // 어댑터 설정
        foodAdapter = new FoodAdapter(this, foodItemList);
        recyclerView.setAdapter(foodAdapter);
    }
}