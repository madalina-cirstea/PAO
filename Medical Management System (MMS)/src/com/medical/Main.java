package com.medical;

public class Main {

    public static void main(String[] args) {
        // create application user
        User user = new User();

        // create hospital and add doctors
        Hospital hospital = new Hospital("Fundeni");
        hospital.addDoctor("general practitioner", "John Savon", "3004926782534", 40, "male");
        hospital.addDoctor("general practitioner", "Milli More", "4004926782566", 35, "female");
        hospital.addDoctor("cardiologist", "Down Hill", "5578928336102", 42, "male");
        hospital.addDoctor("neurologist", "Cassandra Gill", "3004589035726", 39, "female");

        // create hospital manager for hospital
        HospitalManager hospitalManager = new HospitalManager(hospital);

        // start hospital manager
        hospitalManager.start(user);
    }
}
