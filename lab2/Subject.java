package com.uni;

public class Subject {
    private Room room;
    private int noOfStudents;
    private Person teacher;

    public Subject(Room room, int noOfStudents, Person teacher) {
        this.room = room;
        this.noOfStudents = noOfStudents;
        this.teacher = teacher;
    }

    public Subject(String number, String roomType, int floor, int noOfStudents, String name, String surname, String age, long id, String type) {
        this.room = new Room(number, roomType, floor);
        this.noOfStudents = noOfStudents;
        this.teacher = new Person(name, surname, age, id, type);
    }

    public Room getRoom() {
        return room;
    }

    public int getNoOfStudents() {
        return noOfStudents;
    }

    public Person getTeacher() {
        return teacher;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void setNoOfStudents(int noOfStudents) {
        this.noOfStudents = noOfStudents;
    }

    public void setTeacher(Person teacher) {
        this.teacher = teacher;
    }

    public void printSubject() {
        room.printRoom();
        System.out.println(noOfStudents);
        teacher.printPerson();
    }
}
