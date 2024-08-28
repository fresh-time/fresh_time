package com.example.freshtime;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.freshtime.domain.Refrigerator;
import com.example.freshtime.repository.RefrigeratorRepository;

import java.util.HashSet;
import java.util.Set;

public class AddRefFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private EditText addRefName;
    private Button btnSaveRef;

    public AddRefFragment() {
        // Required empty public constructor
    }

    public static AddRefFragment newInstance(String param1, String param2) {
        AddRefFragment fragment = new AddRefFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_ref, container, false);

        // UI 요소 초기화
        addRefName = view.findViewById(R.id.add_ref_name);
        btnSaveRef = view.findViewById(R.id.btn_save_ref);

        // 저장 버튼 클릭 이벤트 처리
        btnSaveRef.setOnClickListener(v -> saveFridgeName());

        return view;
    }

    private void saveFridgeName() {
        // EditText에서 입력된 냉장고 이름 가져오기
        String fridgeName = addRefName.getText().toString().trim();

        // 간단한 유효성 검사
        if (fridgeName.isEmpty()) {
            Toast.makeText(getContext(), "냉장고 이름을 입력하세요.", Toast.LENGTH_SHORT).show();
            return;
        }


        // 중복 체크
        if (RefrigeratorRepository.checkNameDuplicate(fridgeName)) {
            Toast.makeText(getContext(), "이미 저장된 냉장고 이름입니다.", Toast.LENGTH_SHORT).show();
            return;
        }


        // 냉장고 이름 저장
        Refrigerator newFridge = new Refrigerator(fridgeName);
        RefrigeratorRepository.save(newFridge);


        // 저장 후 입력 필드 초기화
        addRefName.setText("");

        // 성공 메시지 표시
        Toast.makeText(getContext(), "냉장고 이름이 저장되었습니다: " + fridgeName, Toast.LENGTH_LONG).show();

        // HomeFragment로 이동
        navigateToHomeFragment();
    }

    private void navigateToHomeFragment() {
        // HomeFragment로 이동하기 위한 FragmentTransaction 생성
        Fragment addFragment = new AddFragment();
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();

        // HomeFragment를 화면에 표시
        transaction.replace(R.id.fragment_container, addFragment);
        transaction.addToBackStack(null);  // 뒤로 가기 버튼으로 이전 Fragment로 돌아갈 수 있게 함
        transaction.commit();
    }
}