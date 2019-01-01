package com.example.gyanesh.myapplication.Models;

import java.io.Serializable;

import androidx.annotation.NonNull;

public class Address implements Serializable {
    public  String USER_ID_KEY = "userId";
    private String mobile;
    private int pinCode;
    private String name;
    private String addLine1;
    private String addLine2;
    private boolean addType;
    private String city;

    public Address(String name,String mobile, String addLine1, String addLine2, String city, int pinCode,boolean addType) {
        this.name = name;
        this.mobile = mobile;
        this.pinCode = pinCode;
        this.addLine1 = addLine1;
        this.addLine2 = addLine2;
        this.addType = addType;
        this.city = city;
    }


    public void setUserId(String USER_ID_KEY) {
        this.USER_ID_KEY = USER_ID_KEY;
    }

    @NonNull
    @Override
    public String toString() {
        return addLine1 + "\n" + addLine2 + "\n" + city + ", " + pinCode + "\n" + mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
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

    public boolean getAddType() {
        return addType;
    }

    public void setAddType(boolean addType) {
        this.addType = addType;
    }

}
