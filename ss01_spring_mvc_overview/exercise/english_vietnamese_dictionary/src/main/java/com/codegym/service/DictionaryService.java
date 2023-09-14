package com.codegym.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DictionaryService {
    private static Map<String,String> dictionary = new HashMap<>();
    static {
        dictionary.put("laptop","máy tính cá nhân");
        dictionary.put("mouse","con chuột");
        dictionary.put("elephant","con voi");
        dictionary.put("gun","khẩu súng");
        dictionary.put("tree","cái cây");
    }

    public String lookUpDictionary (String word){
        for (Map.Entry<String,String> entry:dictionary.entrySet()) {
            if (word.equals(entry.getKey())){
               String result = dictionary.get(entry.getKey());
               return  result;
            }
        }
        return "Không tìm thấy từ: " + word;
    }
}
