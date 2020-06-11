package com.app;

import com.medical.Doctor;

import java.util.*;

public class User {

    public int chooseOperation(Scanner scanner) {
        boolean validResponse = false;
        int opp = -1;

        while (!validResponse) {
            try {
                System.out.println();
                System.out.println("Enter the number of the operation: ");
                opp = scanner.nextInt();
                validResponse = true;
            }
            catch (InputMismatchException e) {
                System.out.println("Operation number must be a number!");
                scanner.nextLine();
            }
        }

        scanner.nextLine();
        return opp;
    }

    public Doctor chooseDoctor(Scanner scanner, Hospital hospital, String specialization) {
        System.out.println("Choose a doctor:");
        hospital.listAllDoctors(specialization);
        while (true) {
            System.out.println("Doctor name: (correctly insert one of the names listed before)");
            String doctorName =  scanner.nextLine();
            Doctor doctor = hospital.getDoctor(doctorName);
            if (doctor != null)
                return doctor;
            System.out.println("Doctor name incorrectly inserted!");
        }
    }

    public String insertDoctorName(Scanner scanner) {
        System.out.println("Doctor name: ");
        return scanner.nextLine();
    }

    public String insertCNP (Scanner scanner) {
        boolean validRespons = false;
        String CNP = "";

        while (!validRespons) {
            try {
                System.out.println("CNP: ");
                CNP = scanner.nextLine();
                if (!CNP.matches("\\d{13}"))
                    throw new IllegalArgumentException("CNP must have 13 digits!");
                validRespons = true;
            }
            catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return CNP;
    }

    public String insertName(Scanner scanner) {
        System.out.println("Name and surname ");
        System.out.println("Family name: ");
        String name  = scanner.nextLine();
        name = name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase();
        System.out.println("Surname: ");
        String surname = scanner.nextLine();
        surname = surname.substring(0,1).toUpperCase() + surname.substring(1).toLowerCase();
        String completeName = name + ' ' + surname;
        return completeName;
    }

    public int insertAge(Scanner scanner) {
        boolean validResponse = false;
        int age = 0;

        while (!validResponse) {
            try {
                System.out.println("Age: ");
                age = scanner.nextInt();
                if (age < 0) {
                    throw new IllegalArgumentException("Age must be a positive number!");
                }
                validResponse = true;
            }
            catch (InputMismatchException e) {
                System.out.println("Age must be a number!");
                scanner.nextLine();
            }
            catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                scanner.nextLine();
            }
        }

        scanner.nextLine();
        return age;
    }

    public int insertId(Scanner scanner) {
        boolean validResponse = false;
        int id = 0;

        while (!validResponse) {
            try {
                System.out.println("ID: ");
                id = scanner.nextInt();
                if (id <= 0) {
                    throw new IllegalArgumentException("ID must be a positive nonzero number!");
                }
                validResponse = true;
            }
            catch (InputMismatchException e) {
                System.out.println("ID must be a number!");
                scanner.nextLine();
            }
            catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                scanner.nextLine();
            }
        }

        scanner.nextLine();
        return id;
    }

    public String insertSex(Scanner scanner) {
        boolean validResponse = false;
        String sex = "";

        while (!validResponse) {
            try {
                System.out.println("Sex: (male/ female)");
                sex = scanner.nextLine().toLowerCase();
                if (!sex.equals("male") && !sex.equals("female") )
                    throw new IllegalArgumentException("Sex must be either male or female!");
                validResponse = true;
            }
            catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return sex;
    }

    public String insertTutorCNP(Scanner scanner) {
        boolean validRespons = false;
        String tutorCNP = "";

        while (!validRespons) {
            try {
                System.out.println("Tutor CNP: ");
                tutorCNP = scanner.nextLine();
                if (!tutorCNP.matches("\\d{13}"))
                    throw new IllegalArgumentException("CNP must have 13 digits!");
                validRespons = true;
            }
            catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return tutorCNP;
    }

    public float insertPension(Scanner scanner) {
        boolean validResponse = false;
        float pension = 0;

        while (!validResponse){
            try {
                System.out.println("Pension: ");
                pension = scanner.nextFloat();
                validResponse = true;
            }
            catch (InputMismatchException e) {
                System.out.println("Pension must be a floating number (use ',' insead of '.')!");
                scanner.nextLine();
            }
        }

        scanner.nextLine();
        return pension;
    }

    public float insertMonthlyIncome(Scanner scanner) {
        boolean validResponse = false;
        float monthlyIncome = 0;

        while (!validResponse) {
            try {
                System.out.println("Monthly income: ");
                monthlyIncome = scanner.nextFloat();
                validResponse = true;
            }
            catch (InputMismatchException e) {
                System.out.println("Monthly income must be a floating number (use ',' insead of '.')!");
                scanner.nextLine();
            }
        }

        scanner.nextLine();
        return monthlyIncome;
    }

    public String insertSpecialization(Scanner scanner) {
        System.out.println("specialization: (general practitioner, cardiologist, neurologist, etc.)");
        return scanner.nextLine().toLowerCase();
    }

    public int insertConsultationType(Scanner scanner, Hospital hospital) {
        boolean validResponse = false;
        int insertedType = -1;

        hospital.listConsultationTypes();

        while (!validResponse) {
            try {
                System.out.println(String.format("Type of consultation: 0 - %d", hospital.getNoOfConsultations() - 1));
                insertedType = scanner.nextInt();
                if (!hospital.validConsultationType(insertedType))
                    throw new IllegalArgumentException(String.format("Consultation type must be a number between 0 - %d!", hospital.getNoOfConsultations() - 1));
                validResponse = true;
            }
            catch (InputMismatchException e) {
                System.out.println("Consultation type must be a number!");
                scanner.nextLine();
            }
            catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                scanner.nextLine();
            }
        }

        scanner.nextLine();
        return insertedType;
    }

    public String insertDayOfWeek(Scanner scanner) {
        String dayOfWeek = "";
        ArrayList<String> daysOfWeek = new ArrayList<>(Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"));

        while (true) {
                System.out.println("Day of week: ");
                dayOfWeek = scanner.nextLine(); //trim();
                dayOfWeek = dayOfWeek.substring(0,1).toUpperCase() + dayOfWeek.substring(1).toLowerCase();
                boolean ok = false;
                for (String day:daysOfWeek)
                    if(dayOfWeek.equals(day)) {
                        ok = true;
                        break;
                    }
                if (ok)
                    break;
                System.out.println("Invalid day of week inserted!");
        }

        return dayOfWeek;
    }

    public int insertStartHour(Scanner scanner) {
        int startHour;
        while (true){
            try {
                System.out.println("Insert start hour: ");
                startHour = scanner.nextInt();
                break;
            }
            catch (InputMismatchException e) {
                System.out.println("The starting hour must be a number!");
                scanner.nextLine();
            }
        }

        scanner.nextLine();
        return startHour;
    }

    public String insertIllness(Scanner scanner, Hospital hospital) {
        String illness;

        while (true){
            System.out.println("Insert name of illness: (hypertension, migraine, heart attack)");
            illness = scanner.nextLine().toLowerCase();
            if (hospital.validIllness(illness))
                break;
            System.out.println("The illness must be either hypertension or migraine or heart attack!");
        }

        return illness;
    }

    public String confirmAppointment(Scanner scanner, String doctorName, String date) {
        System.out.println("Scheduled an appointment with doctor " + doctorName + " for " + date + "?");
        String response = "";

        while (true) {
            try {
                System.out.println("Confirm (yes/ no):");
                response = scanner.nextLine().toLowerCase();
                if (!response.equals("yes") && !response.equals("no"))
                    throw new IllegalArgumentException("Confirmation response must be either yes or no!");
                break;
            }
            catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        return response;
    }

}
