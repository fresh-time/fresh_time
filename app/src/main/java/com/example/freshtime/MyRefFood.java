package com.example.freshtime;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 데이터 초기화
        foodItemList = new ArrayList<>();
        foodItemList.add(new FoodItem("바나나", "/88.7kcal", "~ 24.08.27", R.drawable.banana));
        foodItemList.add(new FoodItem("가지", "/52.0kcal", "~ 24.09.01", R.drawable.eggplant));
        // 더 많은 아이템 추가 가능

        // 어댑터 설정
        foodAdapter = new FoodAdapter(this, foodItemList);
        recyclerView.setAdapter(foodAdapter);
    }
}