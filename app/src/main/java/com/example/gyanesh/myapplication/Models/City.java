package com.example.gyanesh.myapplication.Models;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("City")
public class City extends ParseObject {
    public static final String TITLE = "title";

    public  String getTitle() {
        return getString(TITLE);
    }
}
