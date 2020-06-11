package com.data.generator;

import com.medical.Drug;
import com.medical.MedicalCondition;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class MedicalHistoryGenerator implements DataGenerator<MedicalCondition> {

    private int generatorCapacity = 7;
    List<String> names = Arrays.asList(new String[] {"hypertension", "arrhythmia", "tachycardia", "Apraxia", "Dawson", "Dyslexia", "Hypoxia"});

    public List<MedicalCondition> generate(int noOfMedicalConditions) {
        List<MedicalCondition> medicalConditions = new ArrayList<MedicalCondition>();

        ThreadLocalRandom.current().ints(0, generatorCapacity - 1).distinct().limit(noOfMedicalConditions)
                .forEach(index -> {
                    int noOfDrugs = ThreadLocalRandom.current().nextInt(1, 4);
                    List<Drug> drugs = new ArrayList<Drug>();
                    for (int i = 0; i < noOfDrugs; i++)
                        drugs.add(new Drug("drug_name", rnd.nextInt(5)));
                   medicalConditions.add(new MedicalCondition(names.get(index), generateRandomDate(), drugs));
                });

        return medicalConditions;
    }

    public String generateRandomDate() {
        long minDay = LocalDate.of(2000, 1, 1).toEpochDay();
        long maxDay = LocalDate.of(2020, 1, 1).toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        LocalDate randomDate = LocalDate.ofEpochDay(randomDay);
        return randomDate.toString();
    }
}
