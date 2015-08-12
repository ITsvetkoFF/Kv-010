package com.softserve.edu.moshchonskiy.stubs;

/**
 * Created by Evgen on 8/11/2015.
 */
public class StubJuniorEmployee {

    private String name;

    private int hours;

    private int seniority;

    private final int junPerHour = 10;


    public StubJuniorEmployee() {

        this.name = "Vasiliy";

        this.hours = 200;

        this.seniority  = 8;

    }


    public int getHours() {

        return hours;

    }

    public int getSeniority() {

        return seniority;
    }


    public int getJunPerHour() {

        return junPerHour;
    }
}
