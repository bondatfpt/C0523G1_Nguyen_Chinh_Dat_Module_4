package com.codegym.service;

import org.springframework.stereotype.Service;

@Service
public class CurrencyConversion implements ICurrencyConversion{
    public double convertCurrencyUsdToVnd (double usd){
        double result =usd * 23000;
        return result ;
    }

    public double convertCurrencyVndToUsd (double vnd){
       double result = vnd / 23000;
       return result;
    }
}
