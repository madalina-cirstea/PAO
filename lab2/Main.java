package com.uni;

public class Main {

    public static void main(String[] args) {
        // ex 2
        System.out.println("Persons");
	    Person student = new Person("John", "Doe", "24", 112344, "student");
        Person teacher1 = new Person("Jane", "Roe", "56", 233444, "teacher");
        Person teacher2 = new Person("Joaline", "Buke", "30", 366569, "teacher");

        student.printPerson();
	    teacher1.printPerson();
        teacher2.printPerson();

        // ex 3
        System.out.println("Rooms");
        Room room1 = new Room("12A", "normal",3);
        Room room2 = new Room("12B", "tech", 7);

        room1.printRoom();
        room2.printRoom();

        // ex 4
        System.out.println("Subjects");
        Subject s1 = new Subject(room1, 12, teacher1);
        Subject s2 = new Subject(room2, 20, teacher2);

        s1.printSubject();
        s2.printSubject();

        // ex 4 overloading the constructor
        Subject s3 = new Subject("3C", "normal", 3, 20, "Dan", "Joe", "52", 444555, "teacher");
        s3.printSubject();
    }
}
