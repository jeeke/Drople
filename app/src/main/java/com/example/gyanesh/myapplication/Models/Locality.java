package com.example.gyanesh.myapplication.Models;

import com.parse.ParseClassName;

import static com.example.gyanesh.myapplication.utilClasses.Constants.CITY;

@ParseClassName("Locality")
public class Locality extends City {
    public  String getCity() {
        return getString(CITY);
    }

}
