package com.app;

import com.data.generator.DataGenerator;
import com.data.generator.DoctorGenerator;
import com.data.generator.MedicalHistoryGenerator;
import com.data.generator.PatientGenerator;
import com.data.header.DoctorHeader;
import com.data.header.Header;
import com.data.header.MedicalHistoryHeader;
import com.data.header.PatientHeader;
import com.data.manager.DataManager;
import com.data.manager.DoctorDataManager;
import com.data.manager.MedicalHistoryManager;
import com.data.manager.PatientDataManager;
import com.medical.Doctor;
import com.medical.MedicalCondition;
import com.patient.Patient;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        // create doctor data manager for loading / reading / writing / appending doctors data
        DataManager<Doctor> doctorDataManager = DoctorDataManager.DoctorDataManager();
        // create patient data manager for loading / reading / writing / appending patients data
        DataManager<Patient> patientDataManager = PatientDataManager.PatientDataManager();
        // create medical history manager for loading / reading / writing / appending medical histories data for patients
        DataManager<MedicalCondition> medicalHistoryManager = MedicalHistoryManager.MedicalHistoryManager();

////*******************************************************************************************************
//        // The commented code is the code I used for randomly generating input csv files
//
//        // patient generator is used for generating random patients data using Java Generics Interfaces
//        DataGenerator<Patient> patientGenerator = new PatientGenerator();
//        List<Patient> randomlyGeneratedPatients = patientGenerator.generate(5);
//
//        // doctor generator is used for generating random doctors data
//        DataGenerator<Doctor> doctorGenerator = new DoctorGenerator();
//        List<Doctor> randomlyGeneratedDoctors = doctorGenerator.generate(5);
//
//        List<String> header;
//
//        Header doctorHeader= new DoctorHeader();
//        header = doctorHeader.create();
//        doctorDataManager.write("doctors.csv", header, doctorHeader.toLine(header), randomlyGeneratedDoctors); // write randomly generated data
//
//        Header patientHeader = new PatientHeader();
//        header = patientHeader.create();
//        patientDataManager.write("patients.csv", header, patientHeader.toLine(header), randomlyGeneratedPatients); // write randomly generated data
////*********************************************************************************************************************************

        // create hospital
        Hospital hospital = new Hospital("Fundeni");
        // create hospital manager for hospital
        HospitalManager hospitalManager = new HospitalManager(hospital);

        // loading the csv files when starting the app
        List<Doctor> doctors = doctorDataManager.load("doctors.csv");
        hospitalManager.addDoctors(doctors);

        List<Patient> patients = patientDataManager.load("patients.csv");
        hospitalManager.enrollPatients(patients);

//// **********************************************
//        // after generating the patients and enrolling them in the hospital
//        // we can generate a random medical history for each patient
//
//        // medical history generator is used for generating random medical histories
//        DataGenerator<MedicalCondition> medicalHistoryGenerator = new MedicalHistoryGenerator();
//        Header medicalHistoryHeader = new MedicalHistoryHeader();
//        header = medicalHistoryHeader.create();
//
//        for (Patient patient:patients) {
//            String name = patient.getName();
//            List<MedicalCondition> medicalConditions = medicalHistoryGenerator.generate(3);
//            medicalHistoryManager.write("patientsData/" + patient.getName() + ".csv", header, medicalHistoryHeader.toLine(header), medicalConditions); // write randomly generated data for patient
//        }
//
////***************************************************************************************************

        // for each enrolled patient load the medical history from previously generated files
        for (Patient patient:patients) {
            String fileName = "patientsData/" + patient.getName() + ".csv";
            List<MedicalCondition> medicalConditions = medicalHistoryManager.load(fileName);
            patient.addMedicalConditions(medicalConditions);
        }

        System.out.println("********************************************************");
        System.out.println("CSV files loaded into the hospital's system!");
        System.out.println("********************************************************");

        // create application user
        User user = new User();
        // start hospital manager in user mode
        hospitalManager.start(patientDataManager, doctorDataManager, medicalHistoryManager, user);
    }
}
