package com.example.varatiamanagement.enumClass;

public enum Status {

    VACANT(1,"VACANT"),
    BOOKED(2,"BOOKED"),
    HIRED(3,"HIRED"),
    ONRENT(4,"ONRENT");


    private int code;
    private String values;

    Status(int code, String values) {
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
