package com.example.freshtime;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class AddFoodFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public AddFoodFragment() {
        // Required empty public constructor
    }

    public static AddFoodFragment newInstance(String param1, String param2) {
        AddFoodFragment fragment = new AddFoodFragment();
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
        View view = inflater.inflate(R.layout.fragment_add_food, container, false);

        // 버튼 클릭 이벤트 처리
        ImageButton btnNavigate = view.findViewById(R.id.addfood_user);
        btnNavigate.setOnClickListener(v -> {

            // 이동할 Fragment 생성
            Fragment newFragment = new InputFragment();  // NextFragment로 대체

            // FragmentTransaction을 통해 Fragment 전환
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, newFragment);
            transaction.addToBackStack(null);  // 뒤로 가기 버튼으로 이전 Fragment로 돌아갈 수 있게 함
            transaction.commit();
        });

        return view;
    }
}