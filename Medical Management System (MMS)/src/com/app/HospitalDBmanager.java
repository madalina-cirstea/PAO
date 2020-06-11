package com.app;

import com.DBrepository.DoctorRepository;
import com.DBrepository.DrugRepository;
import com.DBrepository.PatientRepository;
import com.DBrepository.ProgramRepository;
import com.data.header.Header;
import com.data.header.MedicalHistoryHeader;
import com.data.manager.DataManager;
import com.logs.LoggingManager;
import com.medical.Doctor;
import com.medical.MedicalCondition;
import com.patient.Adult;
import com.patient.Minor;
import com.patient.Patient;
import com.patient.Senior;

import javax.print.Doc;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class HospitalDBmanager {
    private DoctorRepository DoctorRepo;
    private PatientRepository PatientRepo;
    private DrugRepository DrugRepo;
    private ProgramRepository ProgramRepo;
    private Scanner scanner;

    public HospitalDBmanager() {
        DoctorRepo = new DoctorRepository();
        PatientRepo = new PatientRepository();
        DrugRepo = new DrugRepository();
        ProgramRepo = new ProgramRepository();
        scanner = new Scanner(System.in);
    }

    public void printMenu() {
        System.out.println();
        System.out.println("********* MENU *********");
        System.out.println("0. Exit.");
        System.out.println("1. List all patients. (SELECT)");
        System.out.println("2. List all doctors. (SELECT)");
        System.out.println("3. List all doctors for a given specialization. (SELECT)");
        System.out.println("4. Insert a new patient. (INSERT)");
        System.out.println("5. Insert a new doctor. (INSERT)");
        System.out.println("6. Update name for a given patient id. (UPDATE)");
        System.out.println("7. Update name for a given doctor id. (UPDATE)");
        System.out.println("8. Delete a patient based on id. (DELETE)");
        System.out.println("9. Delete a doctor based on id. (DELETE)");
        System.out.println("10. Insert a new drug. (INSERT)");
        System.out.println("11. Update dose for a given drug id. (UPDATE)");
        System.out.println("12. Delete a drug based on id. (DELETE)");
        System.out.println("13. List all drugs. (SELECT)");
        System.out.println("14. Insert a new program hour. (INSERT)");
        System.out.println("15. Update the starting hour for a given program id. (UPDATE)");
        System.out.println("16. Delete a program hours based on id. (DELETE)");
        System.out.println("17. List all program hours. (SELECT)");
    }

    public void listAllDoctors() {
        System.out.println("List all doctors:");
        DoctorRepo.listAllDoctors();
    }

    public void listAllDoctors(String specialization) {
        System.out.println("List all doctors which are " + specialization);
        DoctorRepo.listAllDoctors(specialization);
    }

    public void listAllPatients() {
        System.out.println("List all patients:");
        PatientRepo.listAllPatients();
    }

    public Doctor chooseDoctorFromDB() {
        System.out.println("Choose a doctor:");
        DoctorRepo.listAllDoctors("general practitioner");

        while (true) {
            System.out.println("Doctor id: ");
            int doctorId = scanner.nextInt();
            scanner.nextLine();
            Doctor doctor = DoctorRepo.getGeneralPractitionerById(doctorId);
            if (doctor != null) {
                System.out.println("The chosen doctor:");
                doctor.printInfo();
                return doctor;
            }
            System.out.println("Doctor id incorrectly inserted!");
        }
    }


    public void start(User user) {
        printMenu();
        int opp = user.chooseOperation(scanner);

        while (opp != 0) {
            switch (opp) {
                case 1:
                    listAllPatients();
                    break;

                case 2:
                    listAllDoctors();
                    break;

                case 3:
                    String specialization = user.insertSpecialization(scanner);
                    listAllDoctors(specialization);
                    break;

                case 4:
                    System.out.println("Insert a new patient.");
                    String CNP = user.insertCNP(scanner);
                    String name = user.insertName(scanner);
                    int age = user.insertAge(scanner);
                    String sex = user.insertSex(scanner);

                    if (age < 18) {
                        // minor
                        String tutorCNP = user.insertTutorCNP(scanner);
                        Patient tutor = PatientRepo.getPatientByCNP(tutorCNP);
                        if (tutor != null) {
                            Minor minor = new Minor(name, CNP, age, sex, tutor, tutor.getAssignedDoctor());
                            PatientRepo.insertPatient(minor);
                        } else {
                            System.out.println("The tutor is not registered in our database!");
                            System.out.println("The tutor of the minor must be already enrolled at the hospital in order to enroll the minor to the same hospital!");
                            System.out.println("Insert the tutor, than insert the minor!");
                        }
                    } else if (age > 60) {
                        // senior
                        float pension = user.insertPension(scanner);
                        Doctor doctor = chooseDoctorFromDB();
                        Senior senior = new Senior(name, CNP, age, sex, pension, doctor);
                        PatientRepo.insertPatient(senior);
                    } else {
                        // adult
                        float monthlyIncome = user.insertMonthlyIncome(scanner);
                        Doctor doctor = chooseDoctorFromDB();
                        Adult adult = new Adult(name, CNP, age, sex, monthlyIncome, doctor);
                        PatientRepo.insertPatient(adult);
                    }
                    break;

                case 5:
                    System.out.println("Insert a new doctor. (INSERT)");
                    String docSpecialization = user.insertSpecialization(scanner);
                    String docName = user.insertName(scanner);
                    String docCNP = user.insertCNP(scanner);
                    int docAge = user.insertAge(scanner);
                    String docSex = user.insertSex(scanner);
                    Doctor doc = new Doctor(docSpecialization, docName, docCNP, docAge, docSex);
                    DoctorRepo.insertDoctor(doc, "Pantelimon");
                    break;

                case 6:
                    System.out.println("Update name for a given patient id. (UPDATE)");
                    String patient_name = user.insertName(scanner);
                    int patient_id = user.insertId(scanner);
                    PatientRepo.updatePatientName(patient_name, patient_id);
                    break;

                case 7:
                    System.out.println("Update name for a given doctor id. (UPDATE)");
                    String doctorName = user.insertName(scanner);
                    int doctorId = user.insertId(scanner);
                    DoctorRepo.updateDoctorName(doctorName, doctorId);
                    break;

                case 8:
                    System.out.println("Delete a patient based on id. (DELETE)");
                    int id = user.insertId(scanner);
                    PatientRepo.deletePatientById(id);
                    break;

                case 9:
                    System.out.println("Delete a doctor based on id. (DELETE)");
                    int ID = user.insertId(scanner);
                    DoctorRepo.deleteDoctorById(ID);
                    break;

                default:
                    System.out.println("Invalid option!");

                case 10:
                    System.out.println("10. Insert a new drug");
                    DrugRepo.insertDrug("aspacardin", 3);
                    break;

                case 11:
                    System.out.println("11. Update dose for a given drug id. (UPDATE)");
                    DrugRepo.updateDrugDose(5, 1);
                    break;

                case 12:
                    System.out.println("12. Delete a drug based on id. (DELETE)");
                    DrugRepo.deleteDrugById(1);
                    break;

                case 13:
                    System.out.println("13. List all drugs. (SELECT)");
                    DrugRepo.listAllDrugs();
                    break;

                case 14:
                    System.out.println("14. Insert a new program hour. (INSERT)");
                    ProgramRepo.insertProgram(10, 11);
                    break;

                case 15:
                    System.out.println("15. Update the starting hour for a given program id. (UPDATE)");
                    ProgramRepo.updateProgramStart(8, 1);
                    break;

                case 16:
                    System.out.println("16. Delete a program hours based on id. (DELETE)");
                    ProgramRepo.deleteProgramById(1);
                    break;

                case 17:
                    System.out.println("17. List all program hours. (SELECT)");
                    ProgramRepo.listAllProgramHours();
                    break;
                    
                default:
                    System.out.println("Invalid option!");
            }

            printMenu();
            opp = user.chooseOperation(scanner);
        }
    }
}
