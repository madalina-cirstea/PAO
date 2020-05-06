package com.data;

import com.app.Hospital;
import com.medical.Doctor;
import com.medical.GeneralPractitioner;
import com.patient.Adult;
import com.patient.Minor;
import com.patient.Patient;
import com.patient.Senior;

import javax.print.Doc;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Singleton class
public class DoctorDataManager implements DataManager<Doctor> {

    private static DoctorDataManager single_instance = null;

    private DoctorDataManager() {}

    public static DoctorDataManager DoctorDataManager()
    {
        // To ensure only one instance is created
        if (single_instance == null)
        {
            single_instance = new DoctorDataManager();
        }
        return single_instance;
    }

    public List<Doctor> load(String fileName) {
        List<Doctor> doctors = new ArrayList<Doctor>();

        List<List<String>> lines = read(fileName);

        for (int lineIndex = 1; lineIndex < lines.size(); lineIndex++) {
            List<String> line = lines.get(lineIndex);
            if (line.get(0).equals("general practitioner"))
                doctors.add(new GeneralPractitioner(line.get(0), line.get(2), line.get(1), Integer.parseInt(line.get(3)), line.get(4)));
            else
                doctors.add(new Doctor(line.get(0), line.get(2), line.get(1), Integer.parseInt(line.get(3)), line.get(4)));
        }

        return doctors;
    }

    public String createLine(Doctor doctor, List<String> header) {
        StringBuilder line = new StringBuilder();
        for (int headerItemIndex = 0; headerItemIndex < header.size(); headerItemIndex++) {
            String headerItem = header.get(headerItemIndex);

            if (headerItemIndex != 0)
                line.append(',');

            switch (headerItem) {
                case "specialization":
                    line.append(doctor.getSpecialization());
                    break;
                case "CNP":
                    line.append(doctor.getCNP());
                    break;
                case "name":
                    line.append(doctor.getName());
                    break;
                case "age":
                    line.append(doctor.getAge());
                    break;
                case "sex":
                    line.append(doctor.getSex());
                    break;
                default:
                    System.out.println("no match");
            }
        }
        return new String(line);
    }

}
