package com.example.gyanesh.myapplication;


import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gyanesh.myapplication.Models.Order;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.Calendar;
import java.util.Date;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class DonateActivity extends AppCompatActivity {

    //TODO Initialize these values as user fills the details
    private String address = "54525";
    private Date time=Calendar.getInstance().getTime();
    private int clothes = 5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate);
        Toolbar toolbar;
        toolbar = findViewById(R.id.toolbard);
        setSupportActionBar(toolbar);
        androidx.appcompat.app.ActionBar actionBar =  getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        ImageView imageView = (ImageView) findViewById(R.id.edit_address_icon);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DonateActivity.this,AddAddressActivity.class);
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


        TextView Name = findViewById(R.id.Name);
        TextView number = findViewById(R.id.al_number);
        TextView al_default = findViewById(R.id.al_default);
        TextView al_Add1 = findViewById(R.id.al_address);
        TextView al_Add2 = findViewById(R.id.al_address_2);
        TextView Al_city = findViewById(R.id.al_city);
        TextView Al_code = findViewById(R.id.al_code);

//        if(PlaceOrderActivity.callme!=-1) {
//            Name.setText(AddAddressActivity.adressAAA.get(PlaceOrderActivity.callme).name);
//            number.setText(AddAddressActivity.adressAAA.get(PlaceOrderActivity.callme).number);
//            al_Add1.setText(AddAddressActivity.adressAAA.get(PlaceOrderActivity.callme).add1);
//            al_Add2.setText(AddAddressActivity.adressAAA.get(PlaceOrderActivity.callme).add2);
//            Al_code.setText(AddAddressActivity.adressAAA.get(PlaceOrderActivity.callme).pincode);
//            Al_city.setText(AddAddressActivity.adressAAA.get(PlaceOrderActivity.callme).city);
//            al_default.setText(AddAddressActivity.adressAAA.get(PlaceOrderActivity.callme).def_value);
//
//        }
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
