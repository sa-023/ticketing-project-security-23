package com.company.enums;

/*
 * · To create an enum with space, we use the below method. Ex: STATUS("IN PROGRESS")
 */
public enum Gender {

    MALE("Male"), FEMALE("Female");
    private final String value;
    Gender(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }




}
