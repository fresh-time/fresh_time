package com.example.freshtime;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
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
import java.util.List;

import com.example.freshtime.domain.Food;
import com.example.freshtime.domain.Refrigerator;
import com.example.freshtime.repository.RefrigeratorRepository;

public class InputFragment extends Fragment {
    private static final String PREFS_NAME = "FridgePrefs"; // SharedPreferences 파일 이름
    private static final String KEY_FRIDGE_NAMES = "FridgeNames"; // SharedPreferences 키

    private EditText inputName;
    private EditText inputPeriod;
    private TextView inputQuantity;
    private Button btnDecrease;
    private Button btnIncrease;
    private Button btnSaveIngredient;
    Spinner fridgeSpinner;
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

        // Spinner 설정
        fridgeSpinner = view.findViewById(R.id.input_ref);

        // 냉장고 목록을 불러와서 Spinner에 설정
        loadFridgeNames();

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
    private void loadFridgeNames() {
        List<String> fridgeNames = RefrigeratorRepository.getRefrigeratorName();
        // 로그를 사용하여 냉장고 이름 목록을 출력
        Log.d("InputFragment", "Fridge Names: " + fridgeNames.toString());
        if (fridgeNames != null && !fridgeNames.isEmpty()) {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, fridgeNames);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            fridgeSpinner.setAdapter(adapter);
        }
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
        String selectedFridgeName = fridgeSpinner.getSelectedItem().toString();

        // 간단한 유효성 검사
        if (name.isEmpty() || expirationDate.isEmpty()) {
            Toast.makeText(getContext(), "모든 필드를 입력하세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        // 선택한 냉장고 이름으로 Refrigerator 객체 가져오기
        Refrigerator selectedFridge = RefrigeratorRepository.getRefrigeratorByName(selectedFridgeName);
        if (selectedFridge == null) {
            Toast.makeText(getContext(), "선택한 냉장고가 존재하지 않습니다.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Food 객체 생성 및 냉장고에 추가
        Food newFood = new Food(name, expirationDate, quantity);
        selectedFridge.addFood(newFood);

        // 성공 메시지 표시
        String info = "이름: " + name + "\n개수: " + quantity + "\n유통기한: " + expirationDate + "\n냉장고: " + selectedFridgeName;
        Toast.makeText(getContext(), "재료가 저장되었습니다: \n" + info, Toast.LENGTH_LONG).show();

    }


}