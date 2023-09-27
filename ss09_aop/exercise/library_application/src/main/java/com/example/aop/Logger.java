package com.example.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Logger {
    private static int countNumberOfBooks = 0;
    private static int countNumberOfPeoPleVisit = 0;
    @After("execution(* com.example.controller.OrderController.saveOrder(..)) || execution(* com.example.controller.OrderController.giveBookBack(..))")
    public void countActionChangeNumberOfBooks(){
        countNumberOfBooks++;
        System.out.println("The number of actions that cause the number of books to change is " + countNumberOfBooks);
    }

    @After("execution(* com.example.controller.*.*(..))")
    public void countAmountVisit (){
        countNumberOfPeoPleVisit++;
        System.out.println("Number of people who visited the book library " + countNumberOfPeoPleVisit);
    }
}
