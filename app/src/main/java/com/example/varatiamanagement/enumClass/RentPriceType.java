package com.example.varatiamanagement.enumClass;

public enum RentPriceType {

    FIXED(1,"FIXED"),
    VARIABLE(2,"VARIABLE"),
    CENTRATUAL(3,"CENTRATUAL");


    private int code;
    private String values;

    RentPriceType(int code, String values) {
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
