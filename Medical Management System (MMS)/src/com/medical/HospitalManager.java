package com.medical;

import java.time.LocalDate;
import java.util.Scanner;

public class HospitalManager {
    private Hospital hospital;
    private Scanner scanner;

    public HospitalManager(Hospital hospital) {
        this.hospital = hospital;
        this.scanner = new Scanner(System.in);
    }

    public void printMenu() {
        System.out.println();
        System.out.println("********* MENU *********");
        System.out.println("0. Exit.");
        System.out.println("1. Enroll new patient to a general practitioner.");
        System.out.println("2. Print all enrolled patients.");
        System.out.println("3. List all enrolled patients for a given general practitioner.");
        System.out.println("4. List all doctors.");
        System.out.println("5. List all doctors for a given specialization.");
        System.out.println("6. Schedule a medical consultation.");
        System.out.println("7. List all scheduled consultations for a given doctor.");
        System.out.println();
        System.out.println("*** Under construction: ***");
        System.out.println(". Add a new medical condition to a given patient.");
        System.out.println(". Remove a medical condition from a given patient.");
        System.out.println(".Change medication for a given patient.");
    }

    public void start(User user) {
        printMenu();
        int opp = user.chooseOperation(scanner);

        while (opp != 0) {
            switch (opp) {
                case 1:
                    System.out.println("***** Enrol new patient to a general practitioner *****");

                    String CNP = user.insertCNP(scanner);

                    if (hospital.isEnrolled(CNP))
                        System.out.println("Patient already enrolled with this CNP.");
                    else {
                        String name = user.insertName(scanner);
                        int age = user.insertAge(scanner);
                        String sex = user.insertSex(scanner);

                        if (age < 18) {
                            // minor
                            String tutorCNP = user.insertTutorCNP(scanner);

                            if (hospital.isEnrolled(tutorCNP)) {
                                String doctorName = user.chooseDoctorName(scanner, hospital, "general practitioner");
                                hospital.enrollMinor(name, CNP, age, sex, hospital.getEnrolledPatient(tutorCNP), hospital.getDoctor(doctorName));
                            } else {
                                System.out.println("The tutor of the minor must be already enrolled at the hospital in order to enroll the minor to the same hospital!");
                                System.out.println("Enroll the tutor, than enroll the minor!");
                            }
                        } else if (age > 60) {
                            // senior
                            float pension = user.insertPension(scanner);
                            String doctorName = user.chooseDoctorName(scanner, hospital, "general practitioner");
                            hospital.enrollSenior(name, CNP, age, sex, pension, hospital.getDoctor(doctorName));
                        } else {
                            // adult
                            float monthlyIncome = user.insertMonthlyIncome(scanner);
                            String doctorName = user.chooseDoctorName(scanner, hospital, "general practitioner");
                            hospital.enrollAdult(name, CNP, age, sex, monthlyIncome, hospital.getDoctor(doctorName));
                        }
                    }
                    break;

                case 2:
                    System.out.println("**** Print all enrolled patients *****");
                    LocalDate currentDate = LocalDate.now();
                    System.out.println("Patients enrolled in hospital at " + currentDate + ":");
                    hospital.printEnrolledPatients();
                    break;

                case 3:
                    System.out.println("***** List all enrolled patients for a given general practitioner *****");
                    String doctorName = user.insertDoctorName(scanner);
                    if (hospital.isEmployedAs(doctorName, "general practitioner"))
                        hospital.printEnrolledPatients(doctorName);
                    else
                        System.out.println(doctorName + " is not working as a general practitioner in our hospital.");
                    break;

                case 4:
                    System.out.println("***** List all doctors *****");
                    hospital.listAllDoctors();
                    break;

                case 5:
                    System.out.println("***** List all doctors for a given specialization *****");
                    System.out.println("Specialization: (general practitioner, cardiologist, neurologist)");
                    scanner.nextLine();
                    String specialization = scanner.nextLine();
                    hospital.listAllDoctors(specialization);
                    break;

                case 6:
                    System.out.println("***** Schedule a medical consultation *****");
                    System.out.println("Patient information:");
                    System.out.println("CNP:");
                    scanner.nextLine();
                    String patientCNP = scanner.nextLine();

                    Patient patient;

                    if (hospital.isEnrolled(patientCNP)) {
                        // already enrolled patient
                        patient = hospital.getEnrolledPatient(patientCNP);
                    } else {
                        System.out.println("Name: ");
                        String name = scanner.nextLine();

                        System.out.println("Age: ");
                        int age = scanner.nextInt();

                        System.out.println("Sex: (male/ female)");
                        scanner.nextLine();
                        String sex = scanner.nextLine();

                        // occasional patient
                        patient = new Patient(name, patientCNP, age, sex, null);
                    }

                    System.out.println("0. general     1. neurological    2. cardiological");
                    System.out.println("Type of consultation:");
                    int num = scanner.nextInt();
                    if (num == Main.TypeOfConsultation.GENERAL.ordinal()) {
                        hospital.listAllDoctors("general practitioner");
                    } else if (num == Main.TypeOfConsultation.NEUROLOGICAL.ordinal()) {
                        hospital.listAllDoctors("neurologist");
                    } else if (num == Main.TypeOfConsultation.CARDIOLOGICAL.ordinal()) {
                        hospital.listAllDoctors("cardiologist");
                    }

                    System.out.println("Doctor name: ");
                    scanner.nextLine();
                    String name = scanner.nextLine();
                    Doctor doctor = hospital.getDoctor(name);

                    doctor.printProgram();
                    doctor.printAvailableSlots();

                    System.out.println("Choosen time slot:");
                    System.out.println("Day: ");
                    String day = scanner.nextLine();
                    System.out.println("Start hour:");
                    int startHour = scanner.nextInt();
                    System.out.println(name + " " + day + " " + startHour);

                    System.out.println("Confirm (yes/ no):");
                    scanner.nextLine();
                    String confirm = scanner.nextLine();
                    if (confirm.equals("yes")) {
                        System.out.println("yes");
                        doctor.scheduleConsultation(patient, day, startHour);
                    }
                    break;

                case 7:
                    System.out.println("***** List all scheduled consultations for a given doctor *****");
                    System.out.println("Doctor name: ");
                    scanner.nextLine();
                    String docName = scanner.nextLine();
                    Doctor doc = hospital.getDoctor(docName);
                    doc.listScheduledConsultations();
                    break;
                default:
                    System.out.println("Invalid option!");
            }

            printMenu();
            opp = user.chooseOperation(scanner);
        }
    }


}
