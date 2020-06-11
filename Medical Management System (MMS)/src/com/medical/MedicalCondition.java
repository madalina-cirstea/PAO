package com.medical;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MedicalCondition {
    private String nameOfIllness;
    private final String firstDateOfReport;
    private List<Drug> drugs;

    public MedicalCondition(String nameOfIllness, List<Drug> drugs) {
        this.nameOfIllness = nameOfIllness;
        this.firstDateOfReport = LocalDate.now().toString();
        this.drugs = new ArrayList<Drug>(drugs);
    }

    public MedicalCondition(String nameOfIllness, String firstDateOfReport, List<Drug> drugs) {
        this.nameOfIllness = nameOfIllness;
        this.firstDateOfReport = firstDateOfReport;
        this.drugs = drugs;
    }

    public String getNameOfIllness() {
        return nameOfIllness;
    }

    public String getFirstDateOfReport() {
        return firstDateOfReport;
    }

    public List<Drug> getDrugs() {
        return drugs;
    }

    public void print() {
        System.out.println("* " + nameOfIllness + " first date of report " + firstDateOfReport);
        System.out.println("Treatment: ");
        for (int i = 0; i < drugs.size(); i++)
            drugs.get(i).print();
    }
}
