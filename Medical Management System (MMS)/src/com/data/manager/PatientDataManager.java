package com.data.manager;

import com.patient.Adult;
import com.patient.Minor;
import com.patient.Patient;
import com.patient.Senior;

import java.util.ArrayList;
import java.util.List;

// Singleton class
public class PatientDataManager implements DataManager<Patient> {

    private static PatientDataManager single_instance = null;

    private PatientDataManager() {}

    public static PatientDataManager PatientDataManager()
    {
        // To ensure only one instance is created
        if (single_instance == null)
        {
            single_instance = new PatientDataManager();
        }
        return single_instance;
    }


    public List<Patient> load(String fileName) {
        List<Patient> patients = new ArrayList<Patient>();

        List<List<String>> lines = read(fileName);

        for (int lineIndex = 1; lineIndex < lines.size(); lineIndex++) {
            List<String> line = lines.get(lineIndex);

            int age = Integer.parseInt(line.get(2));
            if (age > 60)
                patients.add(new Senior(line.get(1), line.get(0), age, line.get(3), Float.parseFloat(line.get(5)), null));
            else
                patients.add(new Adult(line.get(1), line.get(0), age, line.get(3), Float.parseFloat(line.get(5)), null));
        }

        return patients;
    }

    public String createLine(Patient patient, List<String> header) {
        StringBuilder line = new StringBuilder();
        for (int headerItemIndex = 0; headerItemIndex < header.size(); headerItemIndex++) {
            String headerItem = header.get(headerItemIndex);

            if (headerItemIndex != 0)
                line.append(',');

            switch (headerItem) {
                case "CNP":
                    line.append(patient.getCNP());
                    break;
                case "name":
                    line.append(patient.getName());
                    break;
                case "age":
                    line.append(patient.getAge());
                    break;
                case "sex":
                    line.append(patient.getSex());
                    break;
                case "assignedDoctor":
                    if (patient.getAssignedDoctor() == null)
                        line.append("null");
                    else
                        line.append(patient.getAssignedDoctorName());
                    break;
                case "details":
                    int age = patient.getAge();
                    if (age < 18) {
                        // get tutor's CNP
                        line.append(((Minor)patient).getTutorCNP());
                    }
                    else if (age > 60)
                        line.append(((Senior)patient).getPension());
                    else
                        line.append(((Adult)patient).getMothlyIncome());
                    break;
                default:
                    System.out.println("no match");
            }
        }
        return new String(line);
    }
}
