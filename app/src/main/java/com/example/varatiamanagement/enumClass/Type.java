package com.example.varatiamanagement.enumClass;

public enum Type {

    FLAT(1,"FLAT"),
    CAR(2,"CAR"),
    PLOT(3,"PLOT"),
    SHOP(4,"SHOP"),
    OFFICE(5,"OFFICE");


    private int code;
    private String values;

    Type(int code, String values) {
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
