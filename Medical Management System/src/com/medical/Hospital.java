package com.medical;

import java.util.ArrayList;
import java.util.List;

public class Hospital {
    private String name;
    private List<Doctor> doctors = new ArrayList<Doctor>();
    private List<Patient> patients = new ArrayList<Patient>();

    public Hospital(String name) {
        this.name = name;
    }

    public void addDoctor(String specialization, String name, String CNP, int age, String sex) {
        doctors.add(new Doctor(specialization, name, CNP, age, sex));
    }

    public boolean isEnrolled(String CNP) {
        for (int i = 0; i < patients.size(); i++)
            if (patients.get(i).getCNP().equals(CNP))
                return true;
        return false;
    }

    public boolean isEmployed(String doctorName) {
        for (int i = 0; i < doctors.size(); i++)
            if (doctors.get(i).getName().equals(doctorName))
                return true;
        return false;
    }


    public void listAllGeneralPractitioners() {
        for (int i = 0; i < doctors.size(); i++)
            if (doctors.get(i).getSpecialization().equals("general practitioner")) {
                System.out.println(i + 1 + ". " +  doctors.get(i).getName() + " " + doctors.get(i).getAge() + " years");
            }
    }

    public Doctor getDoctor(int i) {
        return doctors.get(i - 1);
    }

    public Patient getTutor(String CNP) {
        for (int i = 0; i < patients.size(); i++)
            if (patients.get(i).getCNP().equals(CNP))
                return patients.get(i);
        return null;
    }

    public void printPatients() {
        if (patients.size() == 0)
            System.out.println("No patients enrolled yet.");
        for (int i = 0; i < patients.size(); i++)
           patients.get(i).printInfo();
    }

    public void printPatients(String doctorName) {
        if (patients.size() == 0)
            System.out.println("No patients enrolled for " + doctorName + ".");
        for (int i = 0; i < patients.size(); i++)
            if (patients.get(i).getAssignedDoctorName().equals(doctorName))
                patients.get(i).printGeneralInformation();
    }

    public void addAdult(String name, String CNP, int age, String sex, float monthlyIncome, Doctor assignedDoctor) {
        patients.add(new Adult(name, CNP, age, sex, monthlyIncome, assignedDoctor));
    }

    public void addSenior(String name, String CNP, int age, String sex, float pension, Doctor assignedDoctor) {
        patients.add(new Senior(name, CNP, age, sex, pension, assignedDoctor));
    }

    public void addMinor(String name, String CNP, int age, String sex, Patient tutor, Doctor assignedDoctor) {
        patients.add(new Minor(name, CNP, age, sex, tutor, assignedDoctor));
    }

    public void listAllDoctors() {
        for (int i = 0; i < doctors.size(); i++)
            System.out.println(i + 1 + ". " + doctors.get(i).getName() + " " + doctors.get(i).getSpecialization());
    }
}
