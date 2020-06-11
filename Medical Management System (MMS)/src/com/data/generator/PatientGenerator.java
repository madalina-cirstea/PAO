package com.data.generator;

import com.patient.Adult;
import com.patient.Patient;
import com.patient.Senior;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class PatientGenerator implements DataGenerator<Patient>{
    private static int generatorCapacity = 25;
    private static float maxSalary = 100;
    private static float minSalary = 20;
    private static float maxPension = 80;
    private static float minPension = 10;
    private static List<String> familyNames = Arrays.asList(new String[] {"Smith", "Davidof", "Peterson", "Kurly", "Pit"});
    private static List<String> surnames = Arrays.asList(new String[] {"John", "Paul", "Diana", "Milly", "Brad"});
    private static List<String> CNPs;
    private static List<String> names;


    public PatientGenerator() {
        names = new ArrayList<String>();
        for (String familyName:familyNames)
            for (String surname:surnames)
                names.add(familyName + " " + surname);

        CNPs = new ArrayList<String>();
        for (int i = 0; i < generatorCapacity; i++) {
            String CNP = generateRandomCNP();
            while (CNPs.contains(CNP))
                CNP = generateRandomCNP();
            CNPs.add(CNP);
        }
    }

    @Override
    public List<Patient> generate(int noOfPatients) {
        List<Patient> patients = new ArrayList<Patient>();

        ThreadLocalRandom.current().ints(0, generatorCapacity - 1).distinct().limit(noOfPatients)
                .forEach(index -> {
                    int age = generateRandomMajorAge();
                    if (age > 60)
                        patients.add(new Senior(names.get(index), CNPs.get(index), age, generateRandomSex(), generateRandomPension(), null));
                    else
                        patients.add(new Adult(names.get(index), CNPs.get(index), age, generateRandomSex(), generateRandomMonthlyIncome(), null));
                });

        return patients;
    }

    private float generateRandomPension() {
        return minPension + rnd.nextFloat() * (maxPension - minPension);
    }

    private float generateRandomMonthlyIncome() {
        return minSalary + rnd.nextFloat() * (maxSalary - minSalary);
    }
}
