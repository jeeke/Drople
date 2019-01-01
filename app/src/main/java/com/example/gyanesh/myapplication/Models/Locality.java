package com.example.gyanesh.myapplication.Models;

import com.parse.ParseClassName;

@ParseClassName("Locality")
public class Locality extends City {
    public static final String PIN_CODE = "pinCode";
    public static final String CITY = "city";
    public  int getPinCode() {
        return getInt(PIN_CODE);
    }
    public  String getCity() {
        return getString(CITY);
    }

}
