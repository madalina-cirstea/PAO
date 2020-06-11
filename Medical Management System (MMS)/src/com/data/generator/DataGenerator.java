package com.data.generator;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public interface DataGenerator<T> {
    static Random rnd = new Random();
    static int CNPLength = 13;

    public List<T> generate(int no);

    public default String generateRandomCNP() {
        StringBuilder CNP = new StringBuilder(CNPLength);
        for(int i = 0; i < CNPLength; i++)
            CNP.append((char)('0' + rnd.nextInt(10)));
        return CNP.toString();
    }

    public default int generateRandomMajorAge() {
        return ThreadLocalRandom.current().nextInt(18, 100);
    }

    public default String generateRandomSex() {
        if (rnd.nextInt(100) % 2 == 0)
            return new String("male");
        return new String("female");
    }
}
