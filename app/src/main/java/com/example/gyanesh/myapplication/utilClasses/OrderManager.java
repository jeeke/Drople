package com.example.gyanesh.myapplication.utilClasses;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.gyanesh.myapplication.Models.Address;
import com.example.gyanesh.myapplication.Models.Order;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.Calendar;

public class OrderManager {

    public static Order make_order(Address address, int clothes, int payMode, double cost) {
        Order order = new Order();
        order.setAddress(address.toString());
        order.setUserId(ParseUser.getCurrentUser().getObjectId());
        order.setClothes(clothes);
        order.setPayMode(payMode);
        order.setPickupTime(Calendar.getInstance().getTime());
        int orderId = 123;
        //TODO calculate order Id
        order.setOrderId(orderId);
        //TODO Calculate cost
        order.setCost(cost);
        order.setStatus(0);
        return order;
    }

    public static void sendOrder(final Context context, Order order){
        final ProgressDialog dlg = new ProgressDialog(context);
        dlg.setTitle("Please, wait a moment.");
        dlg.setMessage("Placing Your GenericOrder...");
        dlg.show();
        order.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    dlg.dismiss();
                    Toast.makeText(context, "Successfully placed order", Toast.LENGTH_SHORT).show();
                } else {
                    dlg.dismiss();
                    Log.e("Failed to create order", e.toString());
                }
            }
        });
    }
}
