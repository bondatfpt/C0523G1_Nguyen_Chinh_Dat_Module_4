package com.codegym.service;

import com.codegym.repository.DictionaryRepository;
import com.codegym.repository.IDictionaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DictionaryService {
    @Autowired
    private IDictionaryRepository iDictionaryRepository ;
    public String lookUpDictionary(String word) {
       String wordSearch = iDictionaryRepository.lookUpDictionary(word);
       return wordSearch;
    }
}
