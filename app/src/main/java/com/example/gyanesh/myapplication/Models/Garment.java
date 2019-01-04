package com.example.gyanesh.myapplication.Models;

import com.parse.ParseClassName;
import com.parse.ParseObject;

import org.json.JSONException;
import org.json.JSONObject;

import androidx.annotation.Nullable;

@ParseClassName("Garment")
public class Garment extends ParseObject {

    public static final String COUNT = "count";
    public static final String TITLE = "title";
    public static final String PRICE = "price";
    public static final String LOCALITY = "locality";
    public static final String SERVICE_TYPE = "serviceType";


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