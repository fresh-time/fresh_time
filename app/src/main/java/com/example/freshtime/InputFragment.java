package com.example.freshtime;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class InputFragment extends Fragment {

    private EditText inputName;
    private EditText inputPeriod;
    private TextView inputQuantity;
    private Button btnDecrease;
    private Button btnIncrease;
    private Button btnSaveIngredient;
    private int quantity = 1;  // 초기 개수

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public InputFragment() {
        // Required empty public constructor
    }

    public static InputFragment newInstance(String param1, String param2) {
        InputFragment fragment = new InputFragment();
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
        View view = inflater.inflate(R.layout.fragment_input, container, false);

        // 냉장고 목록 정의
        String[] fridgeList = {"Main Fridge", "Small Fridge", "Garage Fridge"};
        // Spinner 설정
        Spinner fridgeSpinner = view.findViewById(R.id.input_ref);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, fridgeList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fridgeSpinner.setAdapter(adapter);

        // UI 요소 초기화
        inputName = view.findViewById(R.id.input_name);
        inputPeriod = view.findViewById(R.id.input_period);
        inputQuantity = view.findViewById(R.id.input_quantity);
        btnDecrease = view.findViewById(R.id.input_decrease);
        btnIncrease = view.findViewById(R.id.input_increase);
        btnSaveIngredient = view.findViewById(R.id.btn_save_ingredient);

        // 버튼 클릭 이벤트 처리
        btnDecrease.setOnClickListener(v -> decreaseQuantity());
        btnIncrease.setOnClickListener(v -> increaseQuantity());
        btnSaveIngredient.setOnClickListener(v -> saveIngredientInfo());
        inputPeriod.setOnClickListener(v -> showDatePickerDialog());

        // Inflate the layout for this fragment
        return view;
    }

    private void showDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                getContext(),
                (view, year1, month1, dayOfMonth) -> {
                    // 월은 0부터 시작하므로 1을 더해줌
                    String selectedDate = year1 + "-" + (month1 + 1) + "-" + dayOfMonth;
                    inputPeriod.setText(selectedDate);
                },
                year, month, day);
        datePickerDialog.show();
    }

    private void decreaseQuantity() {
        if (quantity >= 2) {
            quantity--;
            inputQuantity.setText(String.valueOf(quantity));
        } else {
            Toast.makeText(getContext(), "개수는 1보다 작을 수 없습니다.", Toast.LENGTH_SHORT).show();
        }
    }

    private void increaseQuantity() {
        quantity++;
        inputQuantity.setText(String.valueOf(quantity));
    }

    private void saveIngredientInfo() {
        String name = inputName.getText().toString().trim();
        String expirationDate = inputPeriod.getText().toString().trim();
        String selectedFridge = ((Spinner) getView().findViewById(R.id.input_ref)).getSelectedItem().toString();

        // 간단한 유효성 검사
        if (name.isEmpty() || expirationDate.isEmpty()) {
            Toast.makeText(getContext(), "모든 필드를 입력하세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        // 입력된 정보를 처리 (예: 데이터베이스 저장, 다음 화면으로 이동 등)
        // 이 예제에서는 토스트 메시지로 입력된 정보를 표시함
        String info = "이름: " + name + "\n개수: " + quantity + "\n유통기한: " + expirationDate + "\n냉장고: " + selectedFridge;
        Toast.makeText(getContext(), info, Toast.LENGTH_LONG).show();
    }


}