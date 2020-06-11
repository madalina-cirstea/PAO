package com.uni;

import java.util.Scanner;

public class Ex4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("n= ");
        int n = scanner.nextInt();

        int fct = 1;
        for (int i = 2; i <= n; i++)
            fct = fct * i;

        System.out.println(fct);
    }
}
