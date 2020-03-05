package com.uni;

import java.util.Scanner;

public class Ex7 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("n= ");
        int n = scanner.nextInt();
        int prime = 2;
        int lastPrime = -1;
        boolean isPrime = true;

        while (n >= 2) {
            isPrime = true;

            for (int d = 2; d <= prime / 2; d++)
                if (prime % d == 0) {
                    isPrime = false;
                    break;
                }

            if (isPrime) {
                if (n % prime == 0)
                    lastPrime = prime;

                while (n % prime == 0)
                    n = n / prime;
            }

            prime++;
        }

        if (lastPrime == -1)
            System.out.println("Nu poate fi descompus in factori primi!");
        else
            System.out.println(lastPrime);
    }
}
