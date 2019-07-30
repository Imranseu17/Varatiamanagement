package com.example.varatiamanagement.enumClass;

public enum RentBy {

    DAILY(1,"DAILY"),
    WEEKLY(2,"WEEKLY"),
    MONTHLY(3,"MONTHLY"),
    YEARLY(4,"YEARLY");


    private int code;
    private String values;

    RentBy(int code, String values) {
        this.code = code;
        this.values = values;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getValues() {
        return values;
    }

    public void setValues(String values) {
        this.values = values;
    }
}
