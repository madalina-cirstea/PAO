package com.medical;

import com.patient.Patient;

import java.util.*;

public class Doctor extends Person {
    private String specialization;
    private List<Consultation> scheduledConsultations;
    private LinkedHashMap<String, TimeSlot> program;
    private List<String> availableSlots;


    public Doctor(String specialization, String name, String CNP, int age, String sex) {
        super(name, CNP, age, sex);
        this.specialization = specialization;

        this.program = new LinkedHashMap<String, TimeSlot>();
        // add new method setProgram for doctor (pass corresponding time slots for each day of week)
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
        // need this function for overriding it in extended class GeneralPractitioner which contains a list of assigned patients
    }

    public void printInfo() {
        super.printGeneralInformation();
        System.out.println("specialization: " + specialization);
    }

    public void printProgram() {
        System.out.println();
        System.out.println("Program:");
        for (Map.Entry<String, TimeSlot> entry : program.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue().getStartHour() + " - " + entry.getValue().getEndHour());
        }
    }

    public void printAvailableSlots() {
        System.out.println();
        System.out.println("Available slots:");
        for (int i = 0; i < availableSlots.size(); i++)
            System.out.println(availableSlots.get(i));
        System.out.println();
    }

    public void scheduleConsultation(Patient patient, String date) {
        scheduledConsultations.add(new Consultation(patient, date));
        availableSlots.remove(date);
    }

    public void listScheduledConsultations() {
        if (scheduledConsultations.size() == 0)
            System.out.println("No consultations scheduled for " + this.getName() + ".");
        else {
            for (int i = 0; i < scheduledConsultations.size(); i++)
                scheduledConsultations.get(i).print();
        }
    }

    public boolean isAvailable(String date) {
        for (int i = 0; i < availableSlots.size(); i++)
            if (availableSlots.get(i).equals(date))
                return true;
        return false;
    }

}
