package com.example.gyanesh.myapplication.Models;

import com.parse.ParseClassName;

import static com.example.gyanesh.myapplication.utilClasses.Constants.PAY_MODE;
import static com.example.gyanesh.myapplication.utilClasses.Constants.RETURN_TIME;
import static com.example.gyanesh.myapplication.utilClasses.Constants.TOTAL_COST;

@ParseClassName("Order")
public class Order extends GenericOrder {

    public  int getPayMode() {
        return getInt(PAY_MODE);
    }

    public  double getCost() {
        return getInt(TOTAL_COST);
    }

    public java.util.Date getReturnTime() {
        return getDate(RETURN_TIME);
    }

}

