package com.uni;


import java.util.Scanner;

public class Ex2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("First int: ");
        int x = scanner.nextInt();
        System.out.println("Second int: ");
        int y = scanner.nextInt();
        if (x != y) {
            System.out.println(x + " != " + y);
            if (x < y)
                System.out.println(x + " < " + y);
            else
                System.out.println(x + " > " + y);
        }
        else
            System.out.println(x + " == " + y);
    }
}
