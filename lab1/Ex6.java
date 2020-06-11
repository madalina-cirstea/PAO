package com.uni;

import java.util.Scanner;

public class Ex6 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("n= ");
        int n = scanner.nextInt();
        int x = 0;
        int y = 1;
        int z = 0;

        if (n == 1)
            System.out.println(x);
        else if (n == 2)
            System.out.println(y);
        else {
            for (int i = 3; i <= n; i++) {
                z = x + y;
                x = y;
                y =z;
            }
            System.out.println(z);
        }
    }
}
