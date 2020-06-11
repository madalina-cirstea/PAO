package com.uni;

public class Room {
    private String number;
    private String roomType;
    private int floor;

    public Room(String number, String roomType, int floor) {
        this.number = number;
        this.floor = floor;
        this.roomType = roomType;
    }

    public String getNumber() {
        return number;
    }

    public String getType() {
        return roomType;
    }

    public int getFloor() {
        return floor;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setType(String roomType) {
        this.roomType = roomType;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public void printRoom() {
        System.out.println(number + " " + roomType + " " + floor);
    }
}
