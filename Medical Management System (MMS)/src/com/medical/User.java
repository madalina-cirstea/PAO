package com.medical;

import java.util.Scanner;

public class User {

    public int chooseOperation(Scanner scanner) {
        System.out.println();
        System.out.println("Enter the number o the operation: ");
        int opp = scanner.nextInt();
        scanner.nextLine();
        return opp;
    }

    public String chooseDoctorName(Scanner scanner, Hospital hospital, String specialization) {
        System.out.println("Choose a doctor:");
        hospital.listAllDoctors(specialization);
        System.out.println("Doctor name:");
        return scanner.nextLine();
    }

    public String insertDoctorName(Scanner scanner) {
        System.out.println("Doctor name: ");
        return scanner.nextLine();
    }

    public String insertCNP (Scanner scanner) {
        System.out.println("CNP: ");
        return scanner.nextLine();
    }

    public String insertName(Scanner scanner) {
        System.out.println("Name: ");
        return scanner.nextLine();
    }

    public int insertAge(Scanner scanner) {
        System.out.println("Age: ");
        int age = scanner.nextInt();
        scanner.nextLine();
        return age;
    }

    public String insertSex(Scanner scanner) {
        System.out.println("Sex: (male/ female)");
        return scanner.nextLine();
    }

    public String insertTutorCNP(Scanner scanner) {
        System.out.println("Tutor CNP: ");
        return scanner.nextLine();
    }

    public float insertPension(Scanner scanner) {
        System.out.println("Pension: ");
        float pension = scanner.nextFloat();
        scanner.nextLine();
        return pension;
    }

    public float insertMonthlyIncome(Scanner scanner) {
        System.out.println("Monthly income: ");
        float monthlyIncome = scanner.nextFloat();
        scanner.nextLine();
        return monthlyIncome;
    }

    public String insertSpecialization(Scanner scanner) {
        return scanner.nextLine();
    }

    public int insertConsultationType(Scanner scanner) {
        System.out.println("0. general     1. neurological    2. cardiological");
        System.out.println("Type of consultation: (0 / 1 / 2)");
        int type = scanner.nextInt();
        scanner.nextLine();
        return type;
    }

    public String insertDayOfWeek(Scanner scanner) {
        System.out.println("Day of week: ");
        return scanner.nextLine();
    }

    public int insertStartHour(Scanner scanner) {
        System.out.println("Insert start hour: ");
        int startHour = scanner.nextInt();
        scanner.nextLine();
        return startHour;
    }

    public String insertIllness(Scanner scanner) {
        System.out.println("Insert name of illness: (hypertension, migraine, heart attack)");
        return scanner.nextLine();
    }

    public String confirmAppointment(Scanner scanner, String doctorName, String date) {
        System.out.println("Scheduled an appointment with doctor " + doctorName + " for " + date + "?");
        System.out.println("Confirm (yes/ no):");
        return scanner.nextLine();
    }

}
