package com.example.freshtime;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MyPageFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mypage, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // 뷰를 초기화하고 설정합니다
        ImageButton profileButton;
        profileButton = view.findViewById(R.id.mypage_profile);
        profileButton.setOnClickListener(v -> {
            // 프로필 버튼 클릭 처리
        });

        Switch notificationSwitch = view.findViewById(R.id.mypage_toggle);
        notificationSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // 스위치 상태 변경 처리
        });

        // 다른 뷰도 비슷하게 초기화하고 설정하세요
    }
}