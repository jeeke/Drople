package com.example.gyanesh.myapplication.Models;
import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("Address")
public class Address extends ParseObject{

    public static final String USER_ID_KEY = "userId";
    public static final String MOBILE = "mobile";
    public static final String PIN = "pin";
    public static final String NAME = "name";
    public static final String ADD_LINE1 = "addLine1";
    public static final String ADD_LINE2 = "addLine2";
    public static final String ADD_TYPE = "addType";
    public static final String CITY = "city";

    public void setId(String userId) {
        put(USER_ID_KEY, userId);
    }

    public void setMobile(String mobile) {
        put(MOBILE, mobile);
    }

    public void setPin(int pin) {
        put(PIN,pin);
    }

    public void setName(String name) {
        put(NAME,name);
    }

    public void setAddLine1(String addLine1) {
        put(ADD_LINE1,addLine1);
    }

    public void setAddLine2(String addLine2) {
        put(ADD_LINE2,addLine2);
    }

    public void setAddType(boolean addType) {
        put(ADD_TYPE,addType);
    }

    public void setCity(String city) {
        put(CITY,city);
    }

    public String getId() {
        return getString(USER_ID_KEY);
    }

    public String getMobile() {
        return getString(MOBILE);
    }

    public int getPin() {
        return getInt(PIN);
    }

    public String getName() {
        return getString(NAME);
    }

    public String getAddLine1() {
        return getString(ADD_LINE1);
    }

    public String getAddLine2() {
        return getString(ADD_LINE2);
    }

    public boolean getAddType() {
        return getBoolean(ADD_TYPE);
    }

    public String getCity() {
        return getString(CITY);
    }
}
