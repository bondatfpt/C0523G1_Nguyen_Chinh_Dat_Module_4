package com.codegym.service;

import com.codegym.repository.DictionaryRepository;
import com.codegym.repository.IDictionaryRepository;
import org.springframework.stereotype.Service;



@Service
public class DictionaryService {
    private IDictionaryRepository iDictionaryRepository = new DictionaryRepository();
    public String lookUpDictionary(String word) {
       String wordSearch = iDictionaryRepository.lookUpDictionary(word);
       return wordSearch;
    }
}
