package com.patient;

import com.medical.Doctor;

public class Minor extends Patient {
    private Patient tutor;

    public Minor(String name, String CNP, int age, String sex, Patient tutor, Doctor assignedDoctor) {
        super(name, CNP, age, sex, assignedDoctor);
        this.tutor = tutor;
    }

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.print("legal tutor: ");
        tutor.printGeneralInformation();
    }

    public String getTutorCNP() {
        return tutor.getCNP();
    }
}
