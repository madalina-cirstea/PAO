package com.medical;

public abstract class Person {
    private String name;
    private String CNP;
    private String sex;
    private int age;

    public Person(String name, String CNP, int age, String sex) {
        this.name = name;
        this.CNP = CNP;
        this.sex = sex;
        this.age = age;
    }

    public void printGeneralInformation() {
        System.out.println("Name: " + name + " CNP: " + CNP + " Age: " + age + " Sex: " +  sex);
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getSex() {
        return sex;
    }

    public String getCNP() {
        return CNP;
    }
}
