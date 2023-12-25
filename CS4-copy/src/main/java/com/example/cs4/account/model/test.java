package com.example.cs4.account.model;

import java.util.ArrayList;
import java.util.List;

public class test {
    public static List<Integer> sum (int [] arr){
        List <Integer> integers = new ArrayList<>();
        int max = 0;
        int min = 0;
        int sumMax = 0;
        int sumMin = 0;
        int sumArr = 0;
        for (int i = 0; i < arr.length; i++) {
            sumArr += arr[i];
            if (arr[i] > max){
                max = arr[i];
                sumMin = sumArr - max;
            }
            if (arr[i] < min) {
                min = arr[i];
                sumMax = sumArr - min;
            }
        }
        integers.add(sumMin,sumMax);
        return integers;

    }
    public static void main(String[] args) {
        int arr [] = {1,2,3,4,5};
        System.out.println(sum(arr));
    }
}
