package com.data.generator;

import com.medical.Doctor;
import com.medical.GeneralPractitioner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class DoctorGenerator implements DataGenerator<Doctor>{
    private static int generatorCapacity = 10;
    private List<String> names = Arrays.asList(new String[] {"John Savon", "Milli More", "Down Hill", "Cassandra Gill", "Sam Smithy", "Paula Robers", "Nicolai Tesla", "Elon Duck", "Estera Chelary", "Dana Robert"});
    private List<String> specializations = Arrays.asList(new String[] {"general practitioner", "cardiologist", "neurologist"});
    private List<String> CNPs;

    public DoctorGenerator() {
        CNPs = new ArrayList<String>();
        for (int i = 0; i < generatorCapacity; i++) {
            String CNP = generateRandomCNP();
            while (CNPs.contains(CNP))
                CNP = generateRandomCNP();
            CNPs.add(CNP);
        }
    }

    public List<Doctor> generate(int noOfDoctors) {
        List<Doctor> doctors = new ArrayList<Doctor>();

        ThreadLocalRandom.current().ints(0, generatorCapacity - 1).distinct().limit(noOfDoctors)
                .forEach(index -> {
                    int specializationIndex;

                    if (doctors.size() == 0) {
                        // manually add a general practitioner for avoiding further errors
                        specializationIndex = 0; // index for general practitioner
                    }
                    else
                        specializationIndex = rnd.nextInt(specializations.size());

                    if (specializations.get(specializationIndex).equals("general practitioner"))
                        doctors.add(new GeneralPractitioner(specializations.get(specializationIndex), names.get(index), CNPs.get(index), generateRandomMajorAge(), generateRandomSex()));
                    else
                        doctors.add(new Doctor(specializations.get(specializationIndex), names.get(index), CNPs.get(index), generateRandomMajorAge(), generateRandomSex()));
                });

        return doctors;
    }

}
