package com.medical;

import java.util.ArrayList;
import java.util.List;

public class GeneralPractitioner extends Doctor{
    private List<Patient> assignedPatients;

    public GeneralPractitioner(String specialization, String name, String CNP, int age, String sex) {
        super(specialization, name, CNP, age, sex);
        this.assignedPatients = new ArrayList<Patient>();
    }

    public void printAllAssignedPatients() {
        for(int i = 0; i < assignedPatients.size(); i++){
            assignedPatients.get(i).printGeneralInformation();
        }
    }

    @Override
    public void addAssignedPatient(Patient patient) {
        assignedPatients.add(patient);
    }
}
