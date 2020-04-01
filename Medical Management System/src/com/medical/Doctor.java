package com.medical;

import java.util.ArrayList;
import java.util.List;

public class Doctor extends Person {
    private String specialization;
    private List<Patient> patients = new ArrayList<Patient>();

    public Doctor(String specialization, String name, String CNP, int age, String sex) {
        super(name, CNP, age, sex);
        this.specialization = specialization;
    }

    String getSpecialization() {
        return specialization;
    }

    void printInfo() {
        super.printGeneralInformation();
    }
}
