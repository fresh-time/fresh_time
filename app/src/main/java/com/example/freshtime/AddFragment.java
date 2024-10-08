package com.example.freshtime;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class AddFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddFragment() {
        // Required empty public constructor
    }

    public static AddFragment newInstance(String param1, String param2) {
        AddFragment fragment = new AddFragment();
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
        View view = inflater.inflate(R.layout.fragment_add, container, false);

        // 버튼 클릭 이벤트 처리
        ImageButton btnNavigate = view.findViewById(R.id.add_food);
        btnNavigate.setOnClickListener(v -> {
            // 이동할 Fragment 생성
            Fragment newFragment = new AddFoodFragment();  // NextFragment로 대체

            // FragmentTransaction을 통해 Fragment 전환
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, newFragment);
            transaction.addToBackStack(null);  // 뒤로 가기 버튼으로 이전 Fragment로 돌아갈 수 있게 함
            transaction.commit();
        });

        // "add_share" ImageButton을 찾기
        ImageButton addShareButton = view.findViewById(R.id.add_share);

        // 버튼 클릭 리스너 설정
        addShareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // BlankFragment로 전환
                Fragment blankFragment = new BlankFragment();
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                fragmentTransaction.replace(R.id.fragment_container, blankFragment);
                fragmentTransaction.addToBackStack(null);  // 백스택에 추가하여 뒤로가기 버튼으로 돌아올 수 있게 함
                fragmentTransaction.commit();
            }
        });

        // "addRefButton" ImageButton을 찾기
        ImageButton addRefButton = view.findViewById(R.id.add_ref);

        // 버튼 클릭 리스너 설정
        addRefButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment addRefFragment = new AddRefFragment();
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                fragmentTransaction.replace(R.id.fragment_container, addRefFragment);
                fragmentTransaction.addToBackStack(null);  // 백스택에 추가하여 뒤로가기 버튼으로 돌아올 수 있게 함
                fragmentTransaction.commit();
            }
        });

        return view;

    }
}