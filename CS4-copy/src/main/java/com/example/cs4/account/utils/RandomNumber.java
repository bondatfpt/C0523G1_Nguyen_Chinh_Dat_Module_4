package com.example.cs4.account.utils;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public  class RandomNumber {
    private static Set<String> generatedStrings = new HashSet<>();

    public static String generateRandomString() {
        Random random = new Random();
        int min = 100000;
        int max = 999999;
        int randomNumber;
        do {
            randomNumber = random.nextInt(max - min + 1) + min;
        } while (generatedStrings.contains(String.valueOf(randomNumber)));
        generatedStrings.add(String.valueOf(randomNumber));
        return String.valueOf(randomNumber);
    }
}
