package com.medical;

import java.sql.Time;

public class TimeSlot {
    private int startHour;
    private int endHour;

    public TimeSlot(int startHour, int endHour) {
        this.startHour = startHour;
        this.endHour = endHour;
    }

   public int getStartHour() {
        return startHour;
   }

    public int getEndHour() {
        return endHour;
    }
}
