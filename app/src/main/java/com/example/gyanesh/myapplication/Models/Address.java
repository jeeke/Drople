package com.example.gyanesh.myapplication.Models;

import androidx.annotation.NonNull;

public class Address {
    private long mobile;
    private int pinCode;
    private String addLine1;
    private String addLine2;
    private int addType;
    private String city;

    @NonNull
    @Override
    public String toString() {
        return addLine1 + "\n" + addLine2 + "\n" + city + ", " + pinCode + "\n" + mobile;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public long getMobile() {
        return mobile;
    }

    public void setMobile(long mobile) {
        this.mobile = mobile;
    }

    public int getPinCode() {
        return pinCode;
    }

    public void setPinCode(int pinCode) {
        this.pinCode = pinCode;
    }

    public String getAddLine1() {
        return addLine1;
    }

    public void setAddLine1(String addLine1) {
        this.addLine1 = addLine1;
    }

    public String getAddLine2() {
        return addLine2;
    }

    public void setAddLine2(String addLine2) {
        this.addLine2 = addLine2;
    }

    public int getAddType() {
        return addType;
    }

    public void setAddType(int addType) {
        this.addType = addType;
    }

}
