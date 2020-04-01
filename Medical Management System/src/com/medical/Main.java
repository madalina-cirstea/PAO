package com.medical;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // create hospital
        Hospital hospital = new Hospital("Fundeni");
        hospital.addDoctor("general practitioner", "John Savon", "3004926782534", 40, "male");
        hospital.addDoctor("general practitioner", "Milli More", "4004926782566", 35, "female");
        hospital.addDoctor("cardiologist", "Down Hill", "5578928336102", 42, "male");
        hospital.addDoctor("neurologist", "Cassandra Gill", "3004589035726", 39, "female");

        // print menu
        System.out.println("********* MENU *********");
        System.out.println("0. Exit.");
        System.out.println("1. Enroll new patient.");
        System.out.println("2. Print all patients.");
        System.out.println("3. List all doctors.");
        System.out.println("4. List all patients for a given doctor.");
        System.out.println("5. Add a new medical condition to a given patient."); // to do
        System.out.println("6. Remove a medical condition from a given patient."); // to do
        System.out.println("7.Change medication for a given patient."); // to do
        System.out.println("Enter the number o the operation: ");

        Scanner scanner = new Scanner(System.in);
        int opp = scanner.nextInt();

        while (opp != 0) {
            switch (opp) {
                case 1:
                    System.out.println("***** Enrol new patient *****");
                    System.out.println("General Information: ");

                    System.out.println("Name: ");
                    scanner.nextLine();
                    String name = scanner.nextLine();

                    System.out.println("CNP: ");
                    String CNP = scanner.nextLine();

                    System.out.println("Age: ");
                    int age = scanner.nextInt();

                    System.out.println("Sex: (male/ female)");
                    scanner.nextLine();
                    String sex = scanner.nextLine();

                    if (age < 18) {
                        // minor
                        System.out.println("Tutor information: ");
                        System.out.println("CNP: ");
                        String tutorCNP = scanner.nextLine();
                        System.out.println(tutorCNP);

                        if (hospital.isEnrolled(tutorCNP)) {
                            System.out.println("Choose a general practitioner for the minor: ");
                            hospital.listAllGeneralPractitioners();
                            int num = scanner.nextInt();
                            System.out.println(num);
                            hospital.addMinor(name, CNP, age, sex, hospital.getTutor(tutorCNP), hospital.getDoctor(num));
                        }
                        else {
                            System.out.println("The tutor of the minor must be already enrolled at the hospital in order to enroll the minor to the same hospital!");
                            System.out.println("Enroll the tutor, than enroll the minor!");
                        }
                    }
                    else if (age > 60) {
                        // senior
                        System.out.println("Pension: ");
                        float pension = scanner.nextFloat();

                        System.out.println("Choose a general practitioner: ");
                        hospital.listAllGeneralPractitioners();
                        int num = scanner.nextInt();
                        hospital.addSenior(name, CNP, age, sex, pension, hospital.getDoctor(num));
                    }
                    else {
                        // adult
                        System.out.println("Monthly income: ");
                        float monthlyIncome = scanner.nextFloat();

                        System.out.println("Choose a general practitioner: ");
                        hospital.listAllGeneralPractitioners();
                        int num = scanner.nextInt();
                        hospital.addAdult(name, CNP, age, sex, monthlyIncome, hospital.getDoctor(num));
                    }

                    break;
                case 2:
                    System.out.println("**** Print all patients *****");
                    LocalDate currentDate = LocalDate.now();
                    System.out.println("Patients enrolled in hospital at " + currentDate + ":");
                    hospital.printPatients();
                    break;
                case 3:
                    System.out.println("***** List all doctors *****");
                    hospital.listAllDoctors();
                    break;
                case 4:
                    System.out.println("***** List all patients for a given doctor *****");
                    System.out.println("Doctor name: ");
                    scanner.nextLine();
                    String doctorName = scanner.nextLine();
                    if(hospital.isEmployed(doctorName))
                        hospital.printPatients(doctorName);
                    else
                        System.out.println(doctorName + " is not working in our hospital.");
                    break;
                default:
                    System.out.println("Invalid option!");
            }

            System.out.println();
            System.out.println("Enter the number o the operation: ");
            opp = scanner.nextInt();
        }

    }
}
