package com.example.gyanesh.myapplication.utilClasses;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.gyanesh.myapplication.HistoryActivity;
import com.example.gyanesh.myapplication.Models.OrderModel;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.Calendar;

import static com.example.gyanesh.myapplication.utilClasses.BackgroundData.addresses;

public class OrderManager {

    public static OrderModel make_order(int position, int clothes, int payMode, double cost) {
        OrderModel order = new OrderModel();
        order.setAddress(addresses.get(position).toString());
        order.setUserId(ParseUser.getCurrentUser().getObjectId());
        order.setClothes(clothes);
        order.setPayMode(payMode);
        order.setPickupTime(Calendar.getInstance().getTime());
        int orderId = 123;
        //TODO calculate order Id
        order.setOrderId(orderId);
        //TODO Calculate cost
        order.setCost(cost);
        return order;
    }

    public static void sendOrder(final Context context, OrderModel order){
        final ProgressDialog dlg = new ProgressDialog(context);
        dlg.setTitle("Please, wait a moment.");
        dlg.setMessage("Placing Your Order...");
        dlg.show();
        order.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    dlg.dismiss();
                    Toast.makeText(context, "Successfully placed order", Toast.LENGTH_SHORT).show();
                    context.startActivity(new Intent(context, HistoryActivity.class));
                } else {
                    dlg.dismiss();
                    Log.e("Failed to create order", e.toString());
                }
            }
        });
    }
}
