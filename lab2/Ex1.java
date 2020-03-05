package com.uni;

import java.util.Scanner;

public class Ex1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] studentAges = new int[20];
        int sum = 0;
        int i = 0;

        for (; i < studentAges.length; i++) {
            int value = scanner.nextInt();

            if (value == -1)
                break;
            else {
                studentAges[i] = value;
                sum += value;
            }
        }
        System.out.println((float)sum / (float)i);
    }
}
