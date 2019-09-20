package com.drople.Models;

public class NotificationData {

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

    public NotificationData(String text, int image, String time) {
        this.text = text;
        this.time = time;
        this.image = image;
    }
}
