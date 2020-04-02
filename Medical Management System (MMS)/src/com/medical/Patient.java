package com.medical;

public class Patient extends Person {
    private Doctor assignedDoctor;

    public Patient(String name, String CNP, int age, String sex, Doctor assignedDoctor) {
        super(name, CNP, age, sex);
        this.assignedDoctor = assignedDoctor;
    }

    public void printInfo() {
        super.printGeneralInformation();
        System.out.println("assignedDoctor: " + assignedDoctor.getName());
    }

    public String getAssignedDoctorName() {
        return this.assignedDoctor.getName();
    }

}
