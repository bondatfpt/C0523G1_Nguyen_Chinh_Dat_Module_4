package com.codegym.service;

import org.springframework.stereotype.Service;

@Service
public class CurrencyConversion {
    public double convertCurrencyUsdToVnd (String usd){
        double result =Double.parseDouble(usd) * 23000;
        return result ;
    }

    public double convertCurrencyVndToUsd (String vnd){
       double result = Double.parseDouble(vnd) / 23000;
       return result;
    }
}
