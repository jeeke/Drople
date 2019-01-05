package com.example.gyanesh.myapplication.Models;
import com.parse.ParseClassName;
import com.parse.ParseObject;

import static com.example.gyanesh.myapplication.utilClasses.Constants.ADD_DESC;
import static com.example.gyanesh.myapplication.utilClasses.Constants.ADD_TYPE;
import static com.example.gyanesh.myapplication.utilClasses.Constants.CITY;
import static com.example.gyanesh.myapplication.utilClasses.Constants.LOCALITY;
import static com.example.gyanesh.myapplication.utilClasses.Constants.MOBILE;
import static com.example.gyanesh.myapplication.utilClasses.Constants.NAME;
import static com.example.gyanesh.myapplication.utilClasses.Constants.USER_ID;

@ParseClassName("Address")
public class Address extends ParseObject{

    public void setUserId(String userId) {
        put(USER_ID, userId);
    }

    public void setMobile(String mobile) {
        put(MOBILE, mobile);
    }

    public void setName(String name) {
        put(NAME,name);
    }

    public void setAddDesc(String addDesc) {
        put(ADD_DESC,addDesc);
    }

    public void setAddType(boolean addType) {
        put(ADD_TYPE,addType);
    }

    public void setCity(String city) {
        put(CITY,city);
    }

    public void setLocality(String locality) {
        put(LOCALITY,locality);
    }

    public String getId() {
        return getString(USER_ID);
    }

    public String getMobile() {
        return getString(MOBILE);
    }

    public String getName() {
        return getString(NAME);
    }

    public String getAddDesc() {
        return getString(ADD_DESC);
    }

    public boolean getAddType() {
        return getBoolean(ADD_TYPE);
    }

    public String getCity() {
        return getString(CITY);
    }

    public String getLocality() {
        return getString(LOCALITY);
    }
}
