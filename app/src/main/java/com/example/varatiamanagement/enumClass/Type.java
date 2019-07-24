package com.example.varatiamanagement.enumClass;

public enum Type {

    FLAT(1),
    CAR(2),
    PLOT(3),
    SHOP(4),
    OFFICE(5);


    private int code;

    Type(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Type{" +
                "code=" + code +
                '}';
    }
}
