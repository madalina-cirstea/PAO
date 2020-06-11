package com.uni;

import java.util.Scanner;

public class Ex3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("n= ");
        int n = scanner.nextInt();

        int m = 1;
        int sum = 0;

        while (true) {
            if (m * 3 <= n)
                sum = sum + m * 3;
            else
                break;

            if (m * 5 <= n)
                sum = sum + m * 5;

            m++;
        }

        System.out.println(sum);
    }
}
