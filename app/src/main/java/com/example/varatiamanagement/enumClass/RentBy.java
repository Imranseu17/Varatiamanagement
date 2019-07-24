package com.example.varatiamanagement.enumClass;

public enum RentBy {

    DAILY(1),
    WEEKLY(2),
    MONTHLY(3),
    YEARLY(4);


    private int code;

    RentBy(int code) {
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
        return "RentBy{" +
                "code=" + code +
                '}';
    }
}
