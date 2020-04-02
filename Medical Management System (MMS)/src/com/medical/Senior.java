package com.medical;

public class Senior extends Patient {
    private float pension;

    public Senior (String name, String CNP, int age, String sex, float pension, Doctor assignedDoctor) {
        super(name, CNP, age, sex, assignedDoctor);
        this.pension = pension;
    }

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("pension: " + pension);
    }
}
