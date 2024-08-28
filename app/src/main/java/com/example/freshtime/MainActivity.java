package com.example.freshtime;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.widget.Toast;

import com.example.freshtime.domain.Food;
import com.example.freshtime.domain.Refrigerator;
import com.example.freshtime.repository.RefrigeratorRepository;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.main_bnv);

        //더미데이터 넣기
        // 냉장고 이름 저장
        Refrigerator newFridge = new Refrigerator("main");
        RefrigeratorRepository.save(newFridge);
        // 선택한 냉장고 이름으로 Refrigerator 객체 가져오기
        Refrigerator selectedFridge = RefrigeratorRepository.getRefrigeratorByName("main");

        // Food 객체 생성 및 냉장고에 추가
        Food newFood = new Food("banana", "2024/09/05", 5);
        selectedFridge.addFood(newFood);
        Food newFood2 = new Food("배", "2024/08/03", 2);
        selectedFridge.addFood(newFood2);


        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            Fragment selectedFragment = null;

            int itemId = item.getItemId();
            if (itemId == R.id.menu_home) {
                selectedFragment = new HomeFragment();
            } else if (itemId == R.id.menu_add) {
                selectedFragment = new AddFragment();
            } else if (itemId == R.id.menu_setting) {
                selectedFragment = new MyPageFragment();
            }

            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment)
                        .commit();
                return true;  // Fragment가 정상적으로 교체되었음을 나타내기 위해 true 반환
            }
            return false;  // 선택된 메뉴 항목이 처리되지 않았음을 나타내기 위해 false 반환


        });

        // 기본 선택된 항목을 설정
        if (savedInstanceState == null) {
            bottomNavigationView.setSelectedItemId(R.id.menu_home); // 처음에 선택된 메뉴
        }
    }
}