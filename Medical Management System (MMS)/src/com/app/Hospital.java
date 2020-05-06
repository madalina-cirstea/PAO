package com.app;

import com.data.header.Header;
import com.data.header.PatientHeader;
import com.data.manager.DataManager;
import com.medical.Doctor;
import com.medical.GeneralPractitioner;
import com.patient.Adult;
import com.patient.Minor;
import com.patient.Patient;
import com.patient.Senior;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Hospital {
    private String name;
    private List<Doctor> doctors;
    private List<Patient> enrolledPatients;
    private Map<Integer, String> consultationTypes;
    private ArrayList<String> illnesses;

    public Hospital(String name) {
        this.name = name;
        this.doctors = new ArrayList<Doctor>();
        this.enrolledPatients = new ArrayList<Patient>();

        this.consultationTypes = new HashMap<Integer, String>();
        this.consultationTypes.put(0, "general");
        this.consultationTypes.put(1, "neurological");
        this.consultationTypes.put(2, "cardiological");

        this.illnesses = new ArrayList<String>();
        illnesses.add("hypertension");
        illnesses.add("migraine");
        illnesses.add("heart attack");
    }

    public void listConsultationTypes() {
        System.out.println("Consultation types:");
        consultationTypes.forEach((k, v) -> {
            System.out.println(Integer.toString(k) + ". " + v);
        });
        System.out.println();
    }

    public boolean validIllness(String illness) {
        if (illnesses.contains(illness))
            return true;
        return false;
    }

    public int getNoOfConsultations() {
        return consultationTypes.size();
    }

    public boolean validConsultationType(Integer type){
        return consultationTypes.containsKey(type);
    }

    public void addDoctor(String specialization, String name, String CNP, int age, String sex) {
        if (specialization.equals("general practitioner"))
            doctors.add(new GeneralPractitioner(specialization, name, CNP, age, sex));
        else
            doctors.add(new Doctor(specialization, name, CNP, age, sex));
    }

    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
    }

    public boolean isEnrolled(String CNP) {
        for (int i = 0; i < enrolledPatients.size(); i++)
            if (enrolledPatients.get(i).getCNP().equals(CNP))
                return true;
        return false;
    }

    public boolean isEmployed(String doctorName) {
        for (int i = 0; i < doctors.size(); i++)
            if (doctors.get(i).getName().equals(doctorName))
                return true;
        return false;
    }

    public boolean isEmployedAs(String doctorName, String specializaion) {
        for (int i = 0; i < doctors.size(); i++)
            if (doctors.get(i).getName().equals(doctorName) && doctors.get(i).getSpecialization().equals(specializaion))
                return true;
        return false;
    }

    public Doctor getDoctor(String doctorName) {
        for (int i = 0; i < doctors.size(); i++)
            if (doctors.get(i).getName().equals(doctorName))
                return doctors.get(i);
        return null;
    }

    public List<GeneralPractitioner> getAllGeneralPractitioners() {
        List<GeneralPractitioner> generalPractitioners = new ArrayList<GeneralPractitioner>();
        for (Doctor doctor:doctors)
            if (doctor.getSpecialization().equals("general practitioner"))
                generalPractitioners.add((GeneralPractitioner)doctor);
        return generalPractitioners;
    }

    public Patient getEnrolledPatient(String CNP) {
        for (int i = 0; i < enrolledPatients.size(); i++)
            if (enrolledPatients.get(i).getCNP().equals(CNP))
                return enrolledPatients.get(i);
        return null;
    }

    public void printEnrolledPatients() {
        if (enrolledPatients.size() == 0)
            System.out.println("No patients enrolled yet.");
        for (int i = 0; i < enrolledPatients.size(); i++)
            enrolledPatients.get(i).printInfo();
    }

    public void printEnrolledPatients(String doctorName) {
        boolean foundOne = false;

        for (int i = 0; i < enrolledPatients.size(); i++)
            if (enrolledPatients.get(i).getAssignedDoctorName().equals(doctorName)) {
                enrolledPatients.get(i).printGeneralInformation();
                foundOne = true;
            }

        if (!foundOne)
            System.out.println("No patients enrolled for " + doctorName + ".");
    }

    public void enrollPatient(Patient patient, Doctor assignedDoctor) {
        patient.setAssignedDoctor(assignedDoctor);
        enrolledPatients.add(patient);
        assignedDoctor.addAssignedPatient(patient);
    }

    public void enrollAdult(DataManager<Patient> patientDataManager, String name, String CNP, int age, String sex, float monthlyIncome, Doctor assignedDoctor) {
        enrolledPatients.add(new Adult(name, CNP, age, sex, monthlyIncome, assignedDoctor));
        assignedDoctor.addAssignedPatient(enrolledPatients.get(enrolledPatients.size() - 1));
        Header patientHeader = new PatientHeader();
        List<String> header = patientHeader.create();
        patientDataManager.append("patients.csv", header, patientHeader.toLine(header), enrolledPatients.get(enrolledPatients.size() - 1));
    }

    public void enrollSenior(DataManager<Patient> patientDataManager, String name, String CNP, int age, String sex, float pension, Doctor assignedDoctor) {
        enrolledPatients.add(new Senior(name, CNP, age, sex, pension, assignedDoctor));
        assignedDoctor.addAssignedPatient(enrolledPatients.get(enrolledPatients.size() - 1));
        Header patientHeader = new PatientHeader();
        List<String> header = patientHeader.create();
        patientDataManager.append("patients.csv", header, patientHeader.toLine(header), enrolledPatients.get(enrolledPatients.size() - 1));
    }

    public void enrollMinor(DataManager<Patient> patientDataManager, String name, String CNP, int age, String sex, Patient tutor, Doctor assignedDoctor) {
        enrolledPatients.add(new Minor(name, CNP, age, sex, tutor, assignedDoctor));
        assignedDoctor.addAssignedPatient(enrolledPatients.get(enrolledPatients.size() - 1));
        Header patientHeader = new PatientHeader();
        List<String> header = patientHeader.create();
        patientDataManager.append("patients.csv", header, patientHeader.toLine(header), enrolledPatients.get(enrolledPatients.size() - 1));
    }

    public void listAllDoctors() {
        for (int i = 0; i < doctors.size(); i++)
            System.out.println(i + 1 + ". " + doctors.get(i).getName() + " " + doctors.get(i).getSpecialization());
    }

    public void listAllDoctors(String specialization) {
        int count = 0;
        for (int i = 0; i < doctors.size(); i++)
            if (doctors.get(i).getSpecialization().equals(specialization)) {
                count++;
                System.out.println(count + ". " + doctors.get(i).getName() + " (" + doctors.get(i).getAge() + " years) " + doctors.get(i).getSpecialization());
            }
    }

    public void printScheduledConsultations() {
        for(int i = 0; i < doctors.size(); i++){
            Doctor doctor = doctors.get(i);
            System.out.println(doctor.getName() + ":");
            doctor.listScheduledConsultations();
            System.out.println();
        }
    }

}