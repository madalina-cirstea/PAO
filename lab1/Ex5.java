package com.uni;

import org.w3c.dom.ls.LSOutput;

import javax.crypto.spec.PSource;
import java.util.Scanner;

public class Ex5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("n= ");
        int n = scanner.nextInt();
        boolean isPrime = true;

        if (n == 0 || n == 1)
            isPrime = false;

        for (int d = 2; d <= n / 2; d++)
            if (n % d == 0) {
                isPrime = false;
                break;
            }

        if (isPrime == true)
            System.out.println(n + " is prime!");
        else
            System.out.println(n + " is not prime!");
    }
}
