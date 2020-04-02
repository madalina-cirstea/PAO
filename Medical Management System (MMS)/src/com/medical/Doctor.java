package com.medical;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Doctor extends Person {
    private String specialization;
//    private List<Patient> scheduledPatients;
    private List<Consultation> scheduledConsultations;
    private HashMap<String, TimeSlot> program;
    private List<String> availableSlots;


    public Doctor(String specialization, String name, String CNP, int age, String sex) {
        super(name, CNP, age, sex);
        this.specialization = specialization;
//        this.scheduledPatients = new ArrayList<Patient>();

        this.program = new HashMap<String, TimeSlot>();
        program.put("Monday", new TimeSlot(10, 18));
        program.put("Tuesday", new TimeSlot(10, 18));
        program.put("Wednesday", new TimeSlot(10, 18));
        program.put("Thursday", new TimeSlot(10, 18));
        program.put("Friday", new TimeSlot(10, 18));

        this.availableSlots = new ArrayList<String>();

        for (Map.Entry<String, TimeSlot> entry : program.entrySet()) {
            for (int hour = entry.getValue().getStartHour(); hour < entry.getValue().getEndHour(); hour++)
                availableSlots.add(new String (entry.getKey() + ": " + hour + " - " + (hour + 1)));
        }

        this.scheduledConsultations = new ArrayList<Consultation>();
    }

    public String getSpecialization() {
        return specialization;
    }

    public void addAssignedPatient(Patient patient) {

    }

    public void printInfo() {
        super.printGeneralInformation();
    }

//    public void printAllScheduledPatients() {
//        for (int i = 0; i < scheduledPatients.size(); i++)
//            scheduledPatients.get(i).printGeneralInformation();
//    }

    public void printProgram() {
        System.out.println("Program:");
        for (Map.Entry<String, TimeSlot> entry : program.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue().getStartHour() + " - " + entry.getValue().getEndHour());
        }
    }

    public void printAvailableSlots() {
        for (int i = 0; i < availableSlots.size(); i++)
            System.out.println(availableSlots.get(i));
    }

    public void scheduleConsultation(Patient patient, String day, int startHour) {
        scheduledConsultations.add(new Consultation(patient, new String(day + ": " + startHour + " - " + (startHour + 1))));
        availableSlots.remove(new String(day + ": " + startHour + " - " + (startHour + 1)));
    }

    public void listScheduledConsultations() {
        for (int i = 0; i < scheduledConsultations.size(); i++)
            scheduledConsultations.get(i).print();
    }
}
