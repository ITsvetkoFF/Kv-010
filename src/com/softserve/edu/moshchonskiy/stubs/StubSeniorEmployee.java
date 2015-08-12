package com.softserve.edu.moshchonskiy.stubs;

/**
 * Created by Evgen on 8/11/2015.
 */
public class StubSeniorEmployee {

    private String name;

    private int hours;

    private int seniority;

    private final int seniorPerHour = 20;

    public StubSeniorEmployee() {

        this.name = "Yuriy";

        this.hours = 250;

        this.seniority  = 4;

    }


    public int getHours() {

        return hours;

    }


    public int getSeniority() {

        return seniority;
    }

    public int getSeniorPerHour() {
        return seniorPerHour;
    }

}
