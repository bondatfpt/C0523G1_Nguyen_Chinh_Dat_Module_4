package com.codegym.repository;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
@Repository
public class DictionaryRepository implements IDictionaryRepository {
    private static Map<String, String> dictionary = new HashMap<>();

    static {
        dictionary.put("laptop", "may tinh ca nhan");
        dictionary.put("mouse", "con chuot");
        dictionary.put("elephant", "con voi");
        dictionary.put("gun", "khau sung");
        dictionary.put("tree", "cai cay");
    }

    @Override
    public String lookUpDictionary(String word) {
        for (String key : dictionary.keySet()) {
            if (key.equals(word)) {
                String result = dictionary.get(key);
                return result;
            }
        }
        return "Không tìm thấy từ: " + word;
    }
}

