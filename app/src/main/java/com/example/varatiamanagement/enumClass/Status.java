package com.example.varatiamanagement.enumClass;

public enum Status {

    VACANT(1),
    BOOKED(2),
    HIRED(3),
    ONRENT(4);


    private int code;

    Status(int code) {
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
        return "Status{" +
                "code=" + code +
                '}';
    }
}
