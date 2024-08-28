package com.example.freshtime;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.main_bnv);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            Fragment selectedFragment = null;

            int itemId = item.getItemId();
            if (itemId == R.id.menu_home) {
                selectedFragment = new HomeFragment();
            } else if (itemId == R.id.menu_add) {
                selectedFragment = new AddFragment();
            } else if (itemId == R.id.menu_setting) {
                //selectedFragment = new AddFoodFragment();
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