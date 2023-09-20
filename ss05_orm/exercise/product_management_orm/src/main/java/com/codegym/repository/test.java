package com.codegym.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class test {
    static int[] solution(int l, int r) {
        List<Integer> integerList = new ArrayList<>();
        for (int i = l; i <= r; i++) {
            if (i < 10) {
                integerList.add(i);
            } else if (i > 10) {
               String i1 = String.valueOf(i);
               String arr [] = i1.split("");
               if (arr[0].equals(arr[arr.length - 1])){
                   integerList.add(i);
               }
            }
        }

        int[] arr = new int[integerList.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = integerList.get(i);
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = solution(9, 11);
        System.out.println(Arrays.toString(arr));
    }
}
