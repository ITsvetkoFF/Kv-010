package com.softserve.edu.db.entities;

/**
 * Created by dmakstc on 01.09.2015.
 */
public enum SeverityTypes{
    ONE("1"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5");

    private final String value;
    SeverityTypes(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
