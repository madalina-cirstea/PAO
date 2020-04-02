package com.medical;

import java.util.ArrayList;
import java.util.List;

public class Hospital {
    private String name;
    private List<Doctor> doctors;
    private List<Patient> enrolledPatients;
    private List<Patient> scheduledPatients;


    public Hospital(String name) {
        this.name = name;
        this.doctors = new ArrayList<Doctor>();
        this.enrolledPatients = new ArrayList<Patient>();
        this.scheduledPatients = new ArrayList<Patient>();
    }

    public void addDoctor(String specialization, String name, String CNP, int age, String sex) {
        if (specialization.equals("general practitioner"))
            doctors.add(new GeneralPractitioner(specialization, name, CNP, age, sex));
        else
            doctors.add(new Doctor(specialization, name, CNP, age, sex));
    }

    public boolean isEnrolled(String CNP) {
        for (int i = 0; i < enrolledPatients.size(); i++)
            if (enrolledPatients.get(i).getCNP().equals(CNP))
                return true;
        return false;
    }

    public boolean isEmployed(String doctorName) {
        for (int i = 0; i < doctors.size(); i++)
            if (doctors.get(i).getName().equals(doctorName))
                return true;
        return false;
    }

    public boolean isEmployedAs(String doctorName, String specializaion) {
        for (int i = 0; i < doctors.size(); i++)
            if (doctors.get(i).getName().equals(doctorName) && doctors.get(i).getSpecialization().equals(specializaion))
                return true;
        return false;
    }

    public Doctor getDoctor(String doctorName) {
        for (int i = 0; i < doctors.size(); i++)
            if (doctors.get(i).getName().equals(doctorName))
                return doctors.get(i);
        return null;
    }

    public Patient getEnrolledPatient(String CNP) {
        for (int i = 0; i < enrolledPatients.size(); i++)
            if (enrolledPatients.get(i).getCNP().equals(CNP))
                return enrolledPatients.get(i);
        return null;
    }

    public void printEnrolledPatients() {
        if (enrolledPatients.size() == 0)
            System.out.println("No patients enrolled yet.");
        for (int i = 0; i < enrolledPatients.size(); i++)
            enrolledPatients.get(i).printInfo();
    }

    public void printEnrolledPatients(String doctorName) {
        boolean foundOne = false;

        for (int i = 0; i < enrolledPatients.size(); i++)
            if (enrolledPatients.get(i).getAssignedDoctorName().equals(doctorName)) {
                enrolledPatients.get(i).printGeneralInformation();
                foundOne = true;
            }

        if (!foundOne)
            System.out.println("No patients enrolled for " + doctorName + ".");
    }

    public void enrollAdult(String name, String CNP, int age, String sex, float monthlyIncome, Doctor assignedDoctor) {
        enrolledPatients.add(new Adult(name, CNP, age, sex, monthlyIncome, assignedDoctor));
        assignedDoctor.addAssignedPatient(enrolledPatients.get(enrolledPatients.size() - 1));
    }

    public void enrollSenior(String name, String CNP, int age, String sex, float pension, Doctor assignedDoctor) {
        enrolledPatients.add(new Senior(name, CNP, age, sex, pension, assignedDoctor));
        assignedDoctor.addAssignedPatient(enrolledPatients.get(enrolledPatients.size() - 1));
    }

    public void enrollMinor(String name, String CNP, int age, String sex, Patient tutor, Doctor assignedDoctor) {
        enrolledPatients.add(new Minor(name, CNP, age, sex, tutor, assignedDoctor));
        assignedDoctor.addAssignedPatient(enrolledPatients.get(enrolledPatients.size() - 1));
    }

    public void listAllDoctors() {
        for (int i = 0; i < doctors.size(); i++)
            System.out.println(i + 1 + ". " + doctors.get(i).getName() + " " + doctors.get(i).getSpecialization());
    }

    public void listAllDoctors(String specialization) {
        int count = 0;
        for (int i = 0; i < doctors.size(); i++)
            if (doctors.get(i).getSpecialization().equals(specialization)) {
                count++;
                System.out.println(count + ". " + doctors.get(i).getName() + " (" + doctors.get(i).getAge() + " years) " + doctors.get(i).getSpecialization());
            }
    }

}