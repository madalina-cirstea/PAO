package com.medical;

import jdk.jshell.SourceCodeAnalysis;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    enum TypeOfConsultation {
        GENERAL,
        NEUROLOGICAL,
        CARDIOLOGICAL
    }

    public static void main(String[] args) {

        // create application user
        User user = new User();


        // create hospital
        Hospital hospital = new Hospital("Fundeni");
        hospital.addDoctor("general practitioner", "John Savon", "3004926782534", 40, "male");
        hospital.addDoctor("general practitioner", "Milli More", "4004926782566", 35, "female");
        hospital.addDoctor("cardiologist", "Down Hill", "5578928336102", 42, "male");
        hospital.addDoctor("neurologist", "Cassandra Gill", "3004589035726", 39, "female");


        // create hospital manager for hospital
        HospitalManager hospitalManager = new HospitalManager(hospital);
        hospitalManager.start(user);


//        Scanner scanner = new Scanner(System.in);
//        int opp = scanner.nextInt();
//
//        while (opp != 0) {
//            switch (opp) {
//                case 1:
//                    System.out.println("***** Enrol new patient to a general practitioner *****");
//                    System.out.println("General Information: ");
//
//                    System.out.println("CNP: ");
//                    scanner.nextLine();
//                    String CNP = scanner.nextLine();
//
//                    if (hospital.isEnrolled(CNP))
//                        System.out.println("Patient already enrolled with this CNP.");
//                    else {
//                        System.out.println("Name: ");
//                        String name = scanner.nextLine();
//
//                        System.out.println("Age: ");
//                        int age = scanner.nextInt();
//
//                        System.out.println("Sex: (male/ female)");
//                        scanner.nextLine();
//                        String sex = scanner.nextLine();
//
//                        if (age < 18) {
//                            // minor
//                            System.out.println("Tutor information: ");
//                            System.out.println("CNP: ");
//                            String tutorCNP = scanner.nextLine();
//                            System.out.println(tutorCNP);
//
//                            if (hospital.isEnrolled(tutorCNP)) {
//                                System.out.println("Choose a general practitioner for the minor: ");
//                                hospital.listAllDoctors("general practitioner");
//                                String doctorName = scanner.nextLine();
//                                System.out.println(doctorName);
//                                hospital.enrollMinor(name, CNP, age, sex, hospital.getEnrolledPatient(tutorCNP), hospital.getDoctor(doctorName));
//                            } else {
//                                System.out.println("The tutor of the minor must be already enrolled at the hospital in order to enroll the minor to the same hospital!");
//                                System.out.println("Enroll the tutor, than enroll the minor!");
//                            }
//                        } else if (age > 60) {
//                            // senior
//                            System.out.println("Pension: ");
//                            float pension = scanner.nextFloat();
//
//                            System.out.println("Choose a general practitioner: ");
//                            hospital.listAllDoctors("general practitioner");
//                            String doctorName = scanner.nextLine();
//                            hospital.enrollSenior(name, CNP, age, sex, pension, hospital.getDoctor(doctorName));
//                        } else {
//                            // adult
//                            System.out.println("Monthly income: ");
//                            float monthlyIncome = scanner.nextFloat();
//
//                            System.out.println("Choose a general practitioner: ");
//                            hospital.listAllDoctors("general practitioner");
//                            scanner.nextLine();
//                            String doctorName = scanner.nextLine();
//                            hospital.enrollAdult(name, CNP, age, sex, monthlyIncome, hospital.getDoctor(doctorName));
//                        }
//                    }
//                    break;
//
//                case 2:
//                    System.out.println("**** Print all enrolled patients *****");
//                    LocalDate currentDate = LocalDate.now();
//                    System.out.println("Patients enrolled in hospital at " + currentDate + ":");
//                    hospital.printEnrolledPatients();
//                    break;
//
//                case 3:
//                    System.out.println("***** List all enrolled patients for a given general practitioner *****");
//                    System.out.println("Doctor name: ");
//                    scanner.nextLine();
//                    String doctorName = scanner.nextLine();
//                    if (hospital.isEmployedAs(doctorName, "general practitioner"))
//                        hospital.printEnrolledPatients(doctorName);
//                    else
//                        System.out.println(doctorName + " is not working as a general practitioner in our hospital.");
//                    break;
//
//                case 4:
//                    System.out.println("***** List all doctors *****");
//                    hospital.listAllDoctors();
//                    break;
//
//                case 5:
//                    System.out.println("***** List all doctors for a given specialization *****");
//                    System.out.println("Specialization: (general practitioner, cardiologist, neurologist)");
//                    scanner.nextLine();
//                    String specialization = scanner.nextLine();
//                    hospital.listAllDoctors(specialization);
//                    break;
//
//                case 6:
//                    System.out.println("***** Schedule a medical consultation *****");
//                    System.out.println("Patient information:");
//                    System.out.println("CNP:");
//                    scanner.nextLine();
//                    String patientCNP = scanner.nextLine();
//
//                    Patient patient;
//
//                    if (hospital.isEnrolled(patientCNP)) {
//                        // already enrolled patient
//                        patient = hospital.getEnrolledPatient(patientCNP);
//                    }
//                    else {
//                        System.out.println("Name: ");
//                        String name = scanner.nextLine();
//
//                        System.out.println("Age: ");
//                        int age = scanner.nextInt();
//
//                        System.out.println("Sex: (male/ female)");
//                        scanner.nextLine();
//                        String sex = scanner.nextLine();
//
//                        // occasional patient
//                        patient = new Patient(name, patientCNP, age, sex, null);
//                    }
//
//                    System.out.println("0. general     1. neurological    2. cardiological");
//                    System.out.println("Type of consultation:");
//                    int num = scanner.nextInt();
//                    if (num == TypeOfConsultation.GENERAL.ordinal()) {
//                        hospital.listAllDoctors("general practitioner");
//                    } else if (num == TypeOfConsultation.NEUROLOGICAL.ordinal()) {
//                        hospital.listAllDoctors("neurologist");
//                    } else if (num == TypeOfConsultation.CARDIOLOGICAL.ordinal()) {
//                        hospital.listAllDoctors("cardiologist");
//                    }
//
//                    System.out.println("Doctor name: ");
//                    scanner.nextLine();
//                    String name = scanner.nextLine();
//                    Doctor doctor = hospital.getDoctor(name);
//
//                    doctor.printProgram();
//                    doctor.printAvailableSlots();
//
//                    System.out.println("Choosen time slot:");
//                    System.out.println("Day: ");
//                    String day = scanner.nextLine();
//                    System.out.println("Start hour:");
//                    int startHour = scanner.nextInt();
//                    System.out.println(name + " " + day + " " + startHour);
//
//                    System.out.println("Confirm (yes/ no):");
//                    scanner.nextLine();
//                    String confirm = scanner.nextLine();
//                    if (confirm.equals("yes")) {
//                        System.out.println("yes");
//                        doctor.scheduleConsultation(patient, day, startHour);
//                    }
//                    break;
//
//                case 7:
//                    System.out.println("***** List all scheduled consultations for a given doctor *****");
//                    System.out.println("Doctor name: ");
//                    scanner.nextLine();
//                    String docName = scanner.nextLine();
//                    Doctor doc = hospital.getDoctor(docName);
//                    doc.listScheduledConsultations();
//                    break;
//                default:
//                    System.out.println("Invalid option!");
//            }
//
//            System.out.println();
//            System.out.println("Enter the number o the operation: ");
//            opp = scanner.nextInt();
//        }

    }
}
