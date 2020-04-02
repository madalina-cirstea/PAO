package com.medical;

import java.util.Scanner;

public class User {

    public int chooseOperation(Scanner scanner) {
        System.out.println();
        System.out.println("Enter the number o the operation: ");
        return scanner.nextInt();
    }

    public String insertDoctorName(Scanner scanner) {
        System.out.println("Doctor name: ");
        scanner.nextLine();
        return scanner.nextLine();
    }

    public String insertCNP (Scanner scanner) {
        System.out.println("CNP: ");
        scanner.nextLine();
        return scanner.nextLine();
    }

    public String insertName(Scanner scanner) {
        System.out.println("Name: ");
        return scanner.nextLine();
    }

    public int insertAge(Scanner scanner) {
        System.out.println("Age: ");
        return scanner.nextInt();
    }

    public String insertSex(Scanner scanner) {
        System.out.println("Sex: (male/ female)");
        scanner.nextLine();
        return scanner.nextLine();
    }

    public String insertTutorCNP(Scanner scanner) {
        System.out.println("Tutor CNP: ");
        return scanner.nextLine();
    }

    public float insertPension(Scanner scanner) {
        System.out.println("Pension: ");
        return scanner.nextFloat();
    }

    public float insertMonthlyIncome(Scanner scanner) {
        System.out.println("Monthly income: ");
        return scanner.nextFloat();
    }

    public String chooseDoctorName(Scanner scanner, Hospital hospital, String specialization) {
        System.out.println("Choose a doctor:");
        hospital.listAllDoctors(specialization);
        System.out.println("Doctor name:");
        scanner.nextLine();
        return scanner.nextLine();
    }
}
