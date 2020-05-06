package com.medical;

public class Drug {
    private String name;
    private int recommendedDose; // measured in pills per day

    public Drug(String name, int recommendedDose) {
        this.name = name;
        this.recommendedDose = recommendedDose;
    }

    public String getName() {
        return name;
    }

    public int getRecommendedDose() {
        return recommendedDose;
    }

    public void print() {
        System.out.println(name + " " + recommendedDose + " pills per day");
    }
}
