package com.medical;

public class Minor extends Patient {
    private Patient tutor;

    public Minor(String name, String CNP, int age, String sex, Patient tutor, Doctor assignedDoctor) {
        super(name, CNP, age, sex, assignedDoctor);
        this.tutor = tutor;
    }

    public void printInfo() {
        super.printInfo();
        System.out.println("Legal tutor: ");
        tutor.printGeneralInformation();
    }
}
