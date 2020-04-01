package com.medical;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MedicalCondition {
    private String nameOfIllness;
    private final LocalDate firstDateOfReport;
    private LocalDate lastDateOfReport;
    private List<Drug> drugs = new ArrayList<Drug>();

    public MedicalCondition(String nameOfIllness) {
        this.nameOfIllness = nameOfIllness;
        this.firstDateOfReport = LocalDate.now();
    }
}
