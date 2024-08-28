package com.example.freshtime.repository;

import com.example.freshtime.domain.Refrigerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RefrigeratorRepository {
    private static Map<Long, Refrigerator> refrigerators = new HashMap<>();
    private static Long sequence = 1000L;

    public static void save(Refrigerator refrigerator) {
        refrigerator.setId(sequence);
        refrigerators.put(sequence++, refrigerator);
    }

    public static Refrigerator getRefrigerator(Long id) {
        return refrigerators.get(id);
    }

    public static Refrigerator getRefrigeratorByName(String name) {
        for (Refrigerator value : refrigerators.values()) {
            if (value.getName().equals(name)) return value;
        }
        return null;
    }

    public static List<String> getRefrigeratorName() {
        List<String> names = new ArrayList<>();
        for (Refrigerator refrigerator : refrigerators.values()) {
            names.add(refrigerator.getName());
        }
        return names;
    }

    // 이름 중복 확인 메소드
    // 중복시 true
    public static boolean checkNameDuplicate(String name){
        for (Refrigerator value : refrigerators.values()) {
            if(value.getName().equals(name)) return true;
        }
        return false;
    }

    public static List<Refrigerator> getAllRefrigerators() {
        return new ArrayList<>(refrigerators.values());
    }
}
