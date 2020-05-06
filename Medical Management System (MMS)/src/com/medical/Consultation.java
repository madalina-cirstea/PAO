package com.medical;

import com.patient.Patient;

public class Consultation {
    private Patient patient;
    private String date;

    public Consultation(Patient patient, String date) {
        this.patient = patient;
        this.date = date;
    }

    public void print() {
        System.out.println("Patient: " + patient.getName() + " Date: " + date);
    }
}
