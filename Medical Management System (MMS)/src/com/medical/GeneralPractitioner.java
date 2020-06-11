package com.medical;

import com.patient.Patient;

import java.util.ArrayList;
import java.util.List;

public class GeneralPractitioner extends Doctor {
    private List<Patient> assignedPatients;

    public GeneralPractitioner(String specialization, String name, String CNP, int age, String sex) {
        super(specialization, name, CNP, age, sex);
        this.assignedPatients = new ArrayList<Patient>();
    }

    public void printAssignedPatients() {
        if (assignedPatients.size() == 0)
            System.out.println("No patients enrolled yet to doctor " + this.getName() + ".");
        else {
            for (int i = 0; i < assignedPatients.size(); i++) {
                assignedPatients.get(i).printGeneralInformation();
            }
        }
    }

    @Override
    public void addAssignedPatient(Patient patient) {
        assignedPatients.add(patient);
    }
}
