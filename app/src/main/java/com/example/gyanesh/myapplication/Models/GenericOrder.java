package com.example.gyanesh.myapplication.Models;

import com.parse.ParseClassName;
import com.parse.ParseObject;

import static com.example.gyanesh.myapplication.utilClasses.Constants.GARMENTS;
import static com.example.gyanesh.myapplication.utilClasses.Constants.ORDER_ID;
import static com.example.gyanesh.myapplication.utilClasses.Constants.PICKUP_TIME;
import static com.example.gyanesh.myapplication.utilClasses.Constants.STATUS;
import static com.example.gyanesh.myapplication.utilClasses.Constants.USER_ID;

@ParseClassName("GenericOrder")
public class GenericOrder extends ParseObject {

    public String getUserId() {
        return getString(USER_ID);
    }

    public String getOrderId() {
        return getString(ORDER_ID);
    }

    public java.util.Date getPickupTime() {
        return getDate(PICKUP_TIME);
    }

    public  int getStatus() {
        return getInt(STATUS);
    }

    public  String getGarments() {
        return getString(GARMENTS);
    }
}
