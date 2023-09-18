package com.codegym.service;

import org.springframework.stereotype.Service;
@Service
public class CalculateService implements ICalculateService {
    public double calculate (double firstNum, String operator, double secondNum){
        double result = 0 ;
        switch (operator){
            case "+":
                result = firstNum + secondNum;
                break;
            case "-":
                result = firstNum - secondNum;
                break;
            case "*":
                result = firstNum * secondNum;
                break;
            case "/":
                result = firstNum / secondNum;
                break;
        }
        return result;
    }
}
