package com.drople;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.drople.Models.Address;
import com.drople.Models.GenericOrder;
import com.drople.Models.Order;
import com.drople.utilClasses.DateSelectManager;
import com.drople.utilClasses.OrderManager;

import static com.drople.Server.SERVER_DONATE;
import static com.drople.utilClasses.Constants.SELECT_ADDRESS_REQUEST_CODE;

public class DonateActivity extends BaseActivity implements OrderManager.Listener {

    //TODO Initialize these values as user fills the details
    EditText count;
    private DateSelectManager dateSelectManager;
    OrderManager orderManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate);
        Toolbar toolbar;
        toolbar = findViewById(R.id.toolbard);
        setSupportActionBar(toolbar);
        androidx.appcompat.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        count = findViewById(R.id.count);
        dateSelectManager = new DateSelectManager(this);
        dateSelectManager.setColors();
        orderManager = new OrderManager(this);
        orderManager.updateAddressCard();

        Button confirm = findViewById(R.id.btn_confirm);
        confirm.setOnClickListener(v -> {
            GenericOrder order = checkValidity();
            if(order!=null)
            server.orderDonate(order);
        });

    }

    public GenericOrder checkValidity() {
        Address address = orderManager.selectedAddress;
        String selectedDate = "22 Sep Sun";
//        if (count.getText().toString().equals("")) {
//
//        } else
        if (selectedDate == null) {
            showSnackBar(this, "Please select a date");
            return null;
        } else if (address == null) {
            showSnackBar(this, "Please select an address");
            return null;
        }
        GenericOrder order = new GenericOrder();
        order.address = address;
        order.pickup_time = selectedDate;
        return order;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SELECT_ADDRESS_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                orderManager.setSelectedAddress((Address) data.getSerializableExtra(("address")));
                orderManager.updateAddressCard();
            }
        }
    }

    @Override
    public void onServerCallSuccess(int methodId, String title) {
        super.onServerCallSuccess(methodId, title);
        if(methodId==SERVER_DONATE) finish();
    }
}
