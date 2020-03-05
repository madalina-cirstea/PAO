package com.uni;

public class Person {
    private String name;
    private String surname;
    private String age;
    private long id;
    private String type;

    public Person(String name, String surname, String age, long id, String type) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.id = id;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getAge() {
        return age;
    }

    public long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void printPerson() {
        System.out.println(name + " " + surname + " " + age + " " + id + " " + type);
    }
}
