package com.example.gyanesh.myapplication.Models;

import com.parse.ParseClassName;
import com.parse.ParseObject;

import org.json.JSONException;
import org.json.JSONObject;

import androidx.annotation.Nullable;

import static com.example.gyanesh.myapplication.utilClasses.Constants.COUNT;
import static com.example.gyanesh.myapplication.utilClasses.Constants.LOCALITY;
import static com.example.gyanesh.myapplication.utilClasses.Constants.PRICE;
import static com.example.gyanesh.myapplication.utilClasses.Constants.SERVICE_TYPE;
import static com.example.gyanesh.myapplication.utilClasses.Constants.TITLE;

@ParseClassName("Garment")
public class Garment extends ParseObject {



    public void setCount(int count){
        put(COUNT,count);
    }

    public int getPrice() {
        return getInt(PRICE);
    }

    public int getCount() {
        return getInt(COUNT);
    }

    public int getServiceType() {
        return getInt(SERVICE_TYPE);
    }

    public String getTitle() {
        return getString(TITLE);
    }

    public String getLocality() {
        return getString(LOCALITY);
    }
}