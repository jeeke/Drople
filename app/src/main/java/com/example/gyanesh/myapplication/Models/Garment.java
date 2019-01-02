package com.example.gyanesh.myapplication.Models;

import java.io.Serializable;


//TODO implement parcelable instead of serializable
public class Garment implements Serializable {

    private int number = 0;
    private int garmentId;
    private String type;
    private int price;

    public Garment(int id, String type, int price) {
        this.garmentId = id;
        this.type = type;
        this.price = price;
    }

    public int getGarmentId() {
        return garmentId;
    }

    public int getPrice() {
        return price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }
}