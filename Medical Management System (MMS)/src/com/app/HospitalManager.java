package com.app;

import com.data.header.Header;
import com.data.header.MedicalHistoryHeader;
import com.data.manager.DataManager;
import com.data.manager.DoctorDataManager;
import com.data.manager.MedicalHistoryManager;
import com.data.manager.PatientDataManager;
import com.logs.LoggingManager;
import com.medical.*;
import com.patient.Patient;

import javax.print.Doc;
import java.time.LocalDate;
import java.util.*;
import java.time.LocalDate;

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

    public void start(DataManager<Patient> patientDataManager, DataManager<Doctor> doctorDataManager, DataManager<MedicalCondition> medicalHistoryManager, User user) {
        LoggingManager loggingManager = LoggingManager.LoggingManager();
        printMenu();
        int opp = user.chooseOperation(scanner);

        while (opp != 0) {
            String action = "";
            String timestamp = LocalDate.now().toString();
            String result = "";
            String message= "";

            switch (opp) {
                case 1:
                    System.out.println("***** Enroll new patient to a general practitioner *****");
                    action = "Enroll new patient";

                    String CNP = user.insertCNP(scanner);

                    if (hospital.isEnrolled(CNP)) {
                        System.out.println("Patient already enrolled with this CNP.");
                        result = "failure";
                        message = "Patient already enrolled with given CNP";
                    }
                    else {
                        String name = user.insertName(scanner);
                        int age = user.insertAge(scanner);
                        String sex = user.insertSex(scanner);

                        if (age < 18) {
                            // minor
                            String tutorCNP = user.insertTutorCNP(scanner);

                            if (hospital.isEnrolled(tutorCNP)) {
                                hospital.enrollMinor(patientDataManager, name, CNP, age, sex, hospital.getEnrolledPatient(tutorCNP), user.chooseDoctor(scanner, hospital, "general practitioner"));
                            } else {
                                System.out.println("The tutor of the minor must be already enrolled at the hospital in order to enroll the minor to the same hospital!");
                                System.out.println("Enroll the tutor, than enroll the minor!");
                            }
                        } else if (age > 60) {
                            // senior
                            float pension = user.insertPension(scanner);
                            hospital.enrollSenior(patientDataManager, name, CNP, age, sex, pension, user.chooseDoctor(scanner, hospital, "general practitioner"));
                        } else {
                            // adult
                            float monthlyIncome = user.insertMonthlyIncome(scanner);
                            hospital.enrollAdult(patientDataManager, name, CNP, age, sex, monthlyIncome, user.chooseDoctor(scanner, hospital, "general practitioner"));
                        }

                        result = "success";
                        message = "";
                    }
                    break;

                case 2:
                    System.out.println("**** Print all enrolled patients per hospital *****");
                    action = "Print all enrolled patients per hospital";
                    result = "success";
                    message = "";
                    LocalDate currentDate = LocalDate.now();
                    System.out.println("Patients enrolled in hospital at " + currentDate + ":");
                    hospital.printEnrolledPatients();
                    break;

                case 3: {
                    System.out.println("***** List all enrolled patients for a given general practitioner *****");
                    action = "List all enrolled patients for a given general practitioner";
                    String doctorName = user.insertDoctorName(scanner);
                    if (hospital.isEmployedAs(doctorName, "general practitioner")) {
                        hospital.printEnrolledPatients(doctorName);
                        // alternative
                        // GeneralPractitioner generalPractitioner = (GeneralPractitioner) hospital.getDoctor(doctorName);
                        // generalPractitioner.printAssignedPatients();
                        result = "success";
                        message = "";
                    } else {
                        System.out.println(doctorName + " is not working as a general practitioner in our hospital.");
                        result = "failure";
                        message = "Invalid general practitioner";
                    }
                }
                break;

                case 4:
                    System.out.println("***** List all doctors *****");
                    action = "List all doctors";
                    result = "success";
                    message = "";
                    hospital.listAllDoctors();
                    break;

                case 5: {
                    System.out.println("***** List all doctors for a given specialization *****");
                    action = "List all doctors";
                    System.out.println("Specialization: (general practitioner, cardiologist, neurologist)");
                    String specialization = user.insertSpecialization(scanner);
                    if (!specialization.equals("general practitioner") && !specialization.equals("cardiologist") && !specialization.equals("neurologist")) {
                        System.out.println("Our hospital has no " + specialization + " specialization.");
                        result = "failure";
                        message = "Invalid specialization";
                    }
                    else {
                        hospital.listAllDoctors(specialization);
                        result = "success";
                        message = "";
                    }
                }
                break;

                case 6: {
                    System.out.println("***** Schedule a medical consultation *****");
                    action = "Schedule a medical consultation";
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
                        System.out.println(doctorName + " is not working as a " + specialization + " doctor in our hospital.");
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
                                result = "success";
                                message = "";
                            }
                            else {
                                result = "failure";
                                message = "No confirmation";
                            }
                            break;
                        } else
                            System.out.println("You have chosen an unavailable slot!");
                    }
                }
                break;

                case 7:
                    System.out.println("***** List all scheduled consultations for a given doctor *****");
                    action = "List all scheduled consultations for a given doctor";
                    String doctorName = user.insertDoctorName(scanner);
                    if (hospital.isEmployed(doctorName)) {
                        Doctor doctor = hospital.getDoctor(doctorName);
                        doctor.listScheduledConsultations();
                        result = "success";
                        message = "";
                    } else {
                        System.out.println(doctorName + " is not working as a doctor in our hospital.");
                        result = "failure";
                        message = "Invalid doctor";
                    }
                    break;

                case 8:
                    System.out.println("***** List all scheduled consultations per hospital *****");
                    action = "List all scheduled consultations per hospital";
                    hospital.printScheduledConsultations();
                    result = "success";
                    message = "";
                    break;

                case 9: {
                    System.out.println("***** Add a medical condition to a given patient *****");
                    action = "Add a medical condition to a given patient";
                    String patientCNP = user.insertCNP(scanner);
                    if (hospital.isEnrolled(patientCNP)) {
                        Patient patient = hospital.getEnrolledPatient(patientCNP);
                        String nameOfIllness = user.insertIllness(scanner, hospital);
                        MedicalCondition medicalCondition = new MedicalCondition(nameOfIllness, associatedDrugs.get(nameOfIllness));
                        patient.addMedicalCondition(medicalCondition);
                        Header medicalHistoryHeader = new MedicalHistoryHeader();
                        List<String> header = medicalHistoryHeader.create();
                        medicalHistoryManager.append("patientsData/" + patient.getName() + ".csv", header, medicalHistoryHeader.toLine(header), medicalCondition);
                        result = "success";
                        message = "";
                    } else {
                        System.out.println("Patient with CNP " + patientCNP + " is not enrolled to our hospital.");
                        result = "failure";
                        message = "Invalid patient";
                    }
                }
                break;

                case 10:
                    System.out.println("***** Print the medical history of a given patient *****");
                    action = "Print the medical history of a given patient";
                    String patientCNP = user.insertCNP(scanner);
                    if (hospital.isEnrolled(patientCNP)) {
                        Patient patient = hospital.getEnrolledPatient(patientCNP);
                        patient.printMedicalHistory();
                        result = "success";
                        message = "";
                    } else {
                        System.out.println("Medical history unavailable. Patient with CNP " + patientCNP + " is not enrolled to our hospital.");
                        result = "failure";
                        message = "Invalid patient";
                    }
                    break;

                default:
                    System.out.println("Invalid option!");
            }

            loggingManager.write(loggingManager.createLog(action, timestamp, result, message));

            printMenu();
            opp = user.chooseOperation(scanner);
        }
    }

    public void enrollPatients(List<Patient> patients) {
        Random rnd = new Random();
        List<GeneralPractitioner> generalPractitioners = hospital.getAllGeneralPractitioners();
        for (Patient patient:patients) {
            int doctorIndex = rnd.nextInt(generalPractitioners.size());
            hospital.enrollPatient(patient, generalPractitioners.get(doctorIndex));
        }
    }

    public void addDoctors(List<Doctor> doctors) {
        for (Doctor doctor:doctors)
            hospital.addDoctor(doctor);
    }

}
