package com.patient;

import com.medical.Doctor;
import com.medical.MedicalCondition;
import com.medical.Person;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;

public class Patient extends Person {
    private Doctor assignedDoctor;
    private List<MedicalCondition> medicalHistory;

    public Patient(String name, String CNP, int age, String sex, Doctor assignedDoctor) {
        super(name, CNP, age, sex);
        this.assignedDoctor = assignedDoctor;
        this.medicalHistory = new ArrayList<MedicalCondition>();
    }

    public void setAssignedDoctor(Doctor assignedDoctor) {
        this.assignedDoctor = assignedDoctor;
    }

    public void printInfo() {
        super.printGeneralInformation();
        System.out.println("assignedDoctor: " + assignedDoctor.getName());
    }

    public void printMedicalHistory() {
        printInfo();
        if (medicalHistory.size() == 0)
            System.out.println("Medical history is empty.");
        else {
            for (int i = 0; i < medicalHistory.size(); i++)
                medicalHistory.get(i).print();
        }
    }

    public void addMedicalConditions(List<MedicalCondition> medicalConditions) {
        for (MedicalCondition medicalCondition:medicalConditions)
            medicalHistory.add(medicalCondition);
    }

    public Doctor getAssignedDoctor() {
        return assignedDoctor;
    }

    public String getAssignedDoctorName() {
        return this.assignedDoctor.getName();
    }

    public void addMedicalCondition(MedicalCondition medicalCondition) {
        medicalHistory.add(medicalCondition);
    }

}
