package com.medical;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MedicalCondition {
    private String nameOfIllness;
    private final LocalDate firstDateOfReport;
    private List<Drug> drugs;

    public MedicalCondition(String nameOfIllness, List<Drug> drugs) {
        this.nameOfIllness = nameOfIllness;
        this.firstDateOfReport = LocalDate.now();
        this.drugs = new ArrayList<Drug>(drugs);
    }

    public void print() {
        System.out.println("* " + nameOfIllness + " first date of report " + firstDateOfReport);
        System.out.println("Treatment: ");
        for (int i = 0; i < drugs.size(); i++)
            drugs.get(i).print();
    }
}
