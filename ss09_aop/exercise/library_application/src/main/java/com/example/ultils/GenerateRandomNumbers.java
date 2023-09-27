package com.example.ultils;

public class GenerateRandomNumbers {
    public String generateRandomNumbers() {
        int min = 10000;
        int max = 99999;
        int randomNum = min + (int) (Math.random() * ((max - min) + 1));
        return String.valueOf(randomNum);
    }
}
