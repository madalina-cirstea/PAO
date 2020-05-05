package com.medical;

import java.time.LocalDate;
import java.util.*;

public class HospitalManager {
    enum TypeOfConsultation {
        GENERAL,
        NEUROLOGICAL,
        CARDIOLOGICAL
    }

    private Hospital hospital;
    private Scanner scanner;
    private LinkedHashMap<String, List<Drug>> associatedDrugs;

    public HospitalManager(Hospital hospital) {
        this.hospital = hospital;
        this.scanner = new Scanner(System.in);

        // hospital treatment scheme
        this.associatedDrugs = new LinkedHashMap<String, List<Drug>>();

        associatedDrugs.put("hypertension", new ArrayList<Drug>());
        associatedDrugs.put("migraine", new ArrayList<Drug>());
        associatedDrugs.put("heart attack", new ArrayList<Drug>());

        associatedDrugs.get("hypertension").add(new Drug("Betaloc", 2));
        associatedDrugs.get("hypertension").add(new Drug("Aspacardin", 3));

        associatedDrugs.get("migraine").add(new Drug("Cefalin", 3));
        associatedDrugs.get("migraine").add(new Drug("Xsess", 1));

        associatedDrugs.get("heart attack").add(new Drug("Staxbin", 1));
        associatedDrugs.get("heart attack").add(new Drug("HARTlix", 3));
    }

    public void printMenu() {
        System.out.println();
        System.out.println("********* MENU *********");
        System.out.println("0. Exit.");
        System.out.println("1. Enroll new patient to a general practitioner.");
        System.out.println("2. Print all enrolled patients per hospital.");
        System.out.println("3. List all enrolled patients for a given general practitioner.");
        System.out.println("4. List all doctors.");
        System.out.println("5. List all doctors for a given specialization.");
        System.out.println("6. Schedule a medical consultation.");
        System.out.println("7. List all scheduled consultations for a given doctor.");
        System.out.println("8. List all scheduled consultations per hospital.");
        System.out.println("9. Add a medical condition to a given patient.");
        System.out.println("10. Print the medical history of a given patient.");
    }

    public void start(User user) {
        printMenu();
        int opp = user.chooseOperation(scanner);

        while (opp != 0) {
            switch (opp) {
                case 1:
                    System.out.println("***** Enroll new patient to a general practitioner *****");
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
                                hospital.enrollMinor(name, CNP, age, sex, hospital.getEnrolledPatient(tutorCNP), user.chooseDoctor(scanner, hospital, "general practitioner"));
                            } else {
                                System.out.println("The tutor of the minor must be already enrolled at the hospital in order to enroll the minor to the same hospital!");
                                System.out.println("Enroll the tutor, than enroll the minor!");
                            }
                        } else if (age > 60) {
                            // senior
                            float pension = user.insertPension(scanner);
                            hospital.enrollSenior(name, CNP, age, sex, pension, user.chooseDoctor(scanner, hospital, "general practitioner"));
                        } else {
                            // adult
                            float monthlyIncome = user.insertMonthlyIncome(scanner);
                            hospital.enrollAdult(name, CNP, age, sex, monthlyIncome, user.chooseDoctor(scanner, hospital, "general practitioner"));
                        }
                    }
                    break;

                case 2:
                    System.out.println("**** Print all enrolled patients per hospital *****");
                    LocalDate currentDate = LocalDate.now();
                    System.out.println("Patients enrolled in hospital at " + currentDate + ":");
                    hospital.printEnrolledPatients();
                    break;

                case 3: {
                    System.out.println("***** List all enrolled patients for a given general practitioner *****");
                    String doctorName = user.insertDoctorName(scanner);
                    if (hospital.isEmployedAs(doctorName, "general practitioner")) {
                        hospital.printEnrolledPatients(doctorName);
                        // alternative
                        // GeneralPractitioner generalPractitioner = (GeneralPractitioner) hospital.getDoctor(doctorName);
                        // generalPractitioner.printAssignedPatients();
                    } else
                        System.out.println(doctorName + " is not working as a general practitioner in our hospital.");
                }
                break;

                case 4:
                    System.out.println("***** List all doctors *****");
                    hospital.listAllDoctors();
                    break;

                case 5: {
                    System.out.println("***** List all doctors for a given specialization *****");
                    System.out.println("Specialization: (general practitioner, cardiologist, neurologist)");
                    String specialization = user.insertSpecialization(scanner);
                    if (!specialization.equals("general practitioner") && !specialization.equals("cardiologist") && !specialization.equals("neurologist"))
                        System.out.println("Our hospital has no " + specialization + " specialization.");
                    else
                        hospital.listAllDoctors(specialization);
                }
                break;

                case 6: {
                    System.out.println("***** Schedule a medical consultation *****");
                    System.out.println("Patient information:");
                    Patient patient;
                    String patientCNP = user.insertCNP(scanner);

                    if (hospital.isEnrolled(patientCNP)) {
                        // already enrolled patient
                        patient = hospital.getEnrolledPatient(patientCNP);
                    } else {
                        // create occasional patient for further medical consult
                        String name = user.insertName(scanner);
                        int age = user.insertAge(scanner);
                        String sex = user.insertSex(scanner);
                        patient = new Patient(name, patientCNP, age, sex, null);
                    }

                    int type = user.insertConsultationType(scanner, hospital);
                    String specialization;

                    if (type == HospitalManager.TypeOfConsultation.GENERAL.ordinal()) {
                        specialization = new String("general practitioner");
                    } else if (type == HospitalManager.TypeOfConsultation.NEUROLOGICAL.ordinal()) {
                        specialization = new String("neurologist");
                    } else {
                        specialization = new String("cardiologist");
                    }

                    hospital.listAllDoctors(specialization);
                    String doctorName;

                    while (true) {
                        doctorName = user.insertDoctorName(scanner);
                        if (hospital.isEmployedAs(doctorName, specialization))
                            break;
                        System.out.println(doctorName + " is not working as a " + specialization + "doctor in our hospital.");
                    }

                    Doctor doctor = hospital.getDoctor(doctorName);
                    doctor.printProgram();
                    doctor.printAvailableSlots();

                    while (true) {
                        String day = user.insertDayOfWeek(scanner);
                        int startHour = user.insertStartHour(scanner);
                        String date = new String(day + ": " + startHour + " - " + (startHour + 1));

                        if (doctor.isAvailable(date)) {
                            String confirmation = user.confirmAppointment(scanner, doctorName, date);
                            if (confirmation.equals("yes")) {
                                doctor.scheduleConsultation(patient, date);
                                System.out.println("Consultation scheduled!");
                            }
                            break;
                        } else
                            System.out.println("You have chosen an unavailable slot!");
                    }
                }
                break;

                case 7:
                    System.out.println("***** List all scheduled consultations for a given doctor *****");
                    String doctorName = user.insertDoctorName(scanner);
                    if (hospital.isEmployed(doctorName)) {
                        Doctor doctor = hospital.getDoctor(doctorName);
                        doctor.listScheduledConsultations();
                    } else
                        System.out.println(doctorName + " is not working as a doctor in our hospital.");
                    break;

                case 8:
                    System.out.println("***** List all scheduled consultations per hospital *****");
                    hospital.printScheduledConsultations();
                    break;

                case 9: {
                    System.out.println("***** Add a medical condition to a given patient *****");
                    String patientCNP = user.insertCNP(scanner);
                    if (hospital.isEnrolled(patientCNP)) {
                        Patient patient = hospital.getEnrolledPatient(patientCNP);
                        String nameOfIllness = user.insertIllness(scanner, hospital);

                        MedicalCondition medicalCondition = new MedicalCondition(nameOfIllness, associatedDrugs.get(nameOfIllness));
                        patient.addMedicalCondition(medicalCondition);
                    } else
                        System.out.println("Patient with CNP " + patientCNP + " is not enrolled to our hospital.");
                }
                break;

                case 10:
                    System.out.println("***** Print the medical history of a given patient *****");
                    String patientCNP = user.insertCNP(scanner);
                    if (hospital.isEnrolled(patientCNP)) {
                        Patient patient = hospital.getEnrolledPatient(patientCNP);
                        patient.printMedicalHistory();
                    } else
                        System.out.println("Medical history unavailable. Patient with CNP " + patientCNP + " is not enrolled to our hospital.");
                    break;

                default:
                    System.out.println("Invalid option!");
            }

            printMenu();
            opp = user.chooseOperation(scanner);
        }
    }
}
