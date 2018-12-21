package com.example.gyanesh.myapplication;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.gyanesh.myapplication.Models.Order;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.Calendar;
import java.util.Date;

import androidx.appcompat.app.AppCompatActivity;

public class DonateActivity extends AppCompatActivity {

    //TODO Initialize these values as user fills the details
    private String address = "54525";
    private Date time=Calendar.getInstance().getTime();
    private int clothes = 5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate);
        ImageView imageView = (ImageView) findViewById(R.id.edit_address_icon);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DonateActivity.this,AddressActivity.class);
                startActivity(intent);
            }
        });

        Button confirm = findViewById(R.id.btn_confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO Check if all fields are correctly filled otherwise show Error
                send_order();
            }
        });
    }

    private void send_order() {
        final ProgressDialog dlg = new ProgressDialog(this);
        dlg.setTitle("Please, wait a moment.");
        dlg.setMessage("Placing Your Order...");
        dlg.show();
        Order order = new Order();
        order.setAddress(address);
        order.setUserId(ParseUser.getCurrentUser().getObjectId());
        order.setClothes(clothes);
        order.setPickupTime(time);
        //TODO Create new Object Id
        order.setOrderId(1234);

        order.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    dlg.dismiss();
                    Toast.makeText(DonateActivity.this, "Successfully placed order", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(DonateActivity.this, HistoryActivity.class));
                } else {
                    dlg.dismiss();
                    Log.e("Failed to create order", e.toString());
                }
            }
        });

    }
}
