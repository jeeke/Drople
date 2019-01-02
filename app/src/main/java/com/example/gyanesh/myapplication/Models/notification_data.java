package com.example.gyanesh.myapplication.Models;

public class notification_data {

    private String text,time;
    private int image;

    public String getText() {
        return text;
    }

    public int getImage() {
        return image;
    }

    public String getTime()
    {
        return time;
    }

    public notification_data(String text, int image,String time) {
        this.text = text;
        this.time = time;
        this.image = image;
    }
}
