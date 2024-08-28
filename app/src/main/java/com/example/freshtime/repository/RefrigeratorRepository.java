package com.example.freshtime.repository;

import com.example.freshtime.domain.Refrigerator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RefrigeratorRepository {
    private static Map<Long, Refrigerator> refrigerators=new HashMap<>();
    private static Long sequence=1000L;

    public static void save(Refrigerator refrigerator){
        refrigerator.setId(sequence);
        refrigerators.put(sequence++,refrigerator);
    }

    public static Refrigerator getRefrigerator(Long id){
        return refrigerators.get(id);
    }

    public static Refrigerator getRefrigeratorByName(String name){
        for (Refrigerator value : refrigerators.values()) {
            if(value.getName().equals(name)) return value;
        }
        return null;
    }

    public static String[] getRefrigeratorName(){
        String[] names=new String[refrigerators.size()];
        List<Refrigerator> values = (List<Refrigerator>) refrigerators.values();
        for(int i=0;i< refrigerators.size();i++){
            names[i]=values.get(i).getName();
        }
        return names;
    }
}
