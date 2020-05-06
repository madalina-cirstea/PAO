package com.app;

import com.data.*;
import com.data.generator.DataGenerator;
import com.data.generator.DoctorGenerator;
import com.data.generator.PatientGenerator;
import com.medical.Doctor;
import com.patient.Patient;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        // create doctor data manager for loading / reading / writing / appending doctors data
        DataManager<Doctor> doctorDataManager = DoctorDataManager.DoctorDataManager();
        // create patient data manager for loading / reading / writing / appending patients data
        DataManager<Patient> patientDataManager = PatientDataManager.PatientDataManager();


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
//        Header<Doctor> doctorHeader= new DoctorHeader();
//        header = doctorHeader.create();
//        doctorDataManager.write("doctors.csv", header, doctorHeader.toLine(header), randomlyGeneratedDoctors); // write randomly generated data
//
//        Header<Patient> patientHeader = new PatientHeader();
//        header = patientHeader.create();
//        patientDataManager.write("patients.csv", header, patientHeader.toLine(header), randomlyGeneratedPatients); // write randomly generated data

        // create hospital
        Hospital hospital = new Hospital("Fundeni");
        // create hospital manager for hospital
        HospitalManager hospitalManager = new HospitalManager(hospital);

        // loading the csv files when starting the app
        List<Doctor> doctors = doctorDataManager.load("doctors.csv");
        hospitalManager.addDoctors(doctors);

        List<Patient> patients = patientDataManager.load("patients.csv");
        hospitalManager.enrollPatients(patients);

        System.out.println("********************************************************");
        System.out.println("CSV files loaded into the hospital's system!");
        System.out.println("********************************************************");

        // create application user
        User user = new User();
        // start hospital manager in user mode
        hospitalManager.start(user);
    }
}
