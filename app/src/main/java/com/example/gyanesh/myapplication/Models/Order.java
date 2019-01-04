package com.example.gyanesh.myapplication.Models;

import com.parse.ParseClassName;

@ParseClassName("Order")
public class Order extends GenericOrder {
    public static final String RETURN_TIME_KEY = "returnTime";
    public static final String TOTAL_COST_KEY = "cost";
    public static final String PAY_MODE_KEY = "payMode";

    public  int getPayMode() {
        return getInt(PAY_MODE_KEY);
    }

    public  double getCost() {
        return getInt(TOTAL_COST_KEY);
    }

    public java.util.Date getReturnTime() {
        return getDate(RETURN_TIME_KEY);
    }


    public void setPayMode(int mode) {
        put(PAY_MODE_KEY, mode);
    }

    public void setCost(int cost) {
        put(TOTAL_COST_KEY, cost);
    }

}

