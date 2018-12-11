package com.example.gyanesh.myapplication.Models;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("Order")
public class Order extends ParseObject {


    public static final String USER_ID_KEY = "userId";
    public static final String ORDER_ID_KEY = "orderId";
    public static final String ADDRESS_KEY = "address";
    public static final String STATUS_KEY = "status";
    public static final String PICKUP_TIME_KEY = "pickUpTime";
    public static final String CLOTHES_KEY = "clothes";


    public void setClothes(int clothes) {
        put(CLOTHES_KEY, clothes);
    }

    public String getUserId() {
        return getString(USER_ID_KEY);
    }

    //TODO set readable id to readable ID++
    public void setOrderId(int id) {
        put(ORDER_ID_KEY, id);
    }

    public int getOrderId() {
        return getInt(ORDER_ID_KEY);
    }

//    public String getAddress() {
//        return getString(ADDRESS_KEY);
//    }

    public void setUserId(String userId) {
        put(USER_ID_KEY, userId);
    }

    public void setAddress(String body) {
        put(ADDRESS_KEY, body);
    }

    public void setPickupTime(java.util.Date pickupTime) {
        put(PICKUP_TIME_KEY, pickupTime);
    }

    public java.util.Date getPickupTime() {
        return getDate(PICKUP_TIME_KEY);
    }

    public  int getStatus() {
        return getInt(STATUS_KEY);
    }

    public  int getClothes() {
        return getInt(CLOTHES_KEY);
    }
}
