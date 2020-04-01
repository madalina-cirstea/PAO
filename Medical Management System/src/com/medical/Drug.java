package com.medical;

public class Drug {
    private String name;
    private int recommendedDose; //pills per day

    private Drug(String name, int recommendedDose) {
        this.name = name;
        this.recommendedDose = recommendedDose;
    }
}
