package com.drople;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.drople.Models.Address;
import com.drople.Models.Garment;
import com.drople.utilClasses.DateSelectManager;
import com.drople.utilClasses.OrderManager;
import com.paytm.pgsdk.PaytmPaymentTransactionCallback;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.drople.Server.SERVER_PLACE_ORDER;
import static com.drople.utilClasses.Constants.ADD_CLOTHES_REQUEST_CODE;
import static com.drople.utilClasses.Constants.SELECT_ADDRESS_REQUEST_CODE;
import static com.drople.utilClasses.Constants.TEZ_REQUEST_CODE;

public class PlaceOrderActivity extends BaseActivity implements PaytmPaymentTransactionCallback, AdapterView.OnItemSelectedListener, OrderManager.Listener {

    View addClothes;
    ProgressDialog dlg;
    OrderManager orderManager;
    DateSelectManager dateSelectManager;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == TEZ_REQUEST_CODE) {
            //TODO process based on data in response
            Log.e("result", data.getStringExtra("Status"));
        }

        if (requestCode == ADD_CLOTHES_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                orderManager.setSelectedGarments(new ArrayList<>(((Map<Integer, Garment>) data.getSerializableExtra("selectedGarments")).values()));
                orderManager.setTotal_amount(data.getIntExtra("total_amount", 0));
                RecyclerView listView = findViewById(R.id.selectedList);
//                listView.setVisibility(View.VISIBLE);
                listView.setAdapter(orderManager.getSelectClothesAdapter());
                listView.setLayoutManager(new LinearLayoutManager(this));
            }
        }

        if (requestCode == SELECT_ADDRESS_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                orderManager.setSelectedAddress((Address) data.getSerializableExtra(("address")));
                orderManager.updateAddressCard();
            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        orderManager = new OrderManager(this);
        setContentView(R.layout.activity_place_order);
        addClothes = findViewById(R.id.add_clothes);
        addClothes.setOnClickListener(v1 -> {
            Intent intent1 = new Intent(v1.getContext(), AddedClothesActivity.class);
            startActivityForResult(intent1, ADD_CLOTHES_REQUEST_CODE);
        });

        Toolbar toolbar;
        toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        androidx.appcompat.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        //FOr Address Selection
        orderManager.updateAddressCard();

        //For handling date and time selection
        dateSelectManager = new DateSelectManager(this);
        TextView textView = findViewById(R.id.slot_layout).findViewById(R.id.heading);
        textView.setText("Select Pick Up Time");
        dateSelectManager.setColors();

        dlg = new ProgressDialog(this);
        Button confirm = findViewById(R.id.btn_confirm);
        confirm.setOnClickListener(view -> {
            orderManager.setSelectedDate(dateSelectManager.getSelectedDate());
            if (orderManager.checkValidity()) {
//                dlg = new ProgressDialog(PlaceOrderActivity.this);
//                dlg.setTitle("Placing Your Order");
//                dlg.show();
                if (orderManager.redirectToPayment()) {
                    orderManager.verifyAndPlaceOrder(null,server);
                }
            }
        });

        Spinner spinner = findViewById(R.id.spinner5);
        spinner.setOnItemSelectedListener(this);
        List<String> options = new ArrayList<>();
        options.add("Cash On Delivery");
        options.add("Google pay");
        options.add("Paytm");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, options);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

    }

    @Override
    public void onServerCallSuccess(int methodId, String title) {
        super.onServerCallSuccess(methodId, title);
        if(methodId==SERVER_PLACE_ORDER){
            finish();
        }
    }

    //1 for paytm
    //0 for Tez
    //2 for Cash on delivery
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        orderManager.setPayMode(position);
        TextView mode = findViewById(R.id.textView25);
        String Mode = parent.getSelectedItem().toString();
        mode.setText(Mode);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        orderManager.setPayMode(2);
    }

    //all these overriden method is to detect the payment result accordingly
    @Override
    public void onTransactionResponse(Bundle bundle) {
        orderManager.verifyAndPlaceOrder(bundle,server);
    }

    //Paytm Callbacks
    @Override
    public void networkNotAvailable() {
        dlg.dismiss();
        showSnackBar(this,"Network Error");
//        Toast.makeText(this, "Network error", Toast.LENGTH_LONG).show();
    }

    @Override
    public void clientAuthenticationFailed(String s) {
        dlg.dismiss();
        showSnackBar(this,s);
//        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }

    @Override
    public void someUIErrorOccurred(String s) {
        dlg.dismiss();
        showSnackBar(this,s);
//        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onErrorLoadingWebPage(int i, String s, String s1) {
        dlg.dismiss();
        showSnackBar(this,s);
//        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressedCancelTransaction() {
        dlg.dismiss();
        showSnackBar(this,"Back Pressed");
//        Toast.makeText(this, , Toast.LENGTH_LONG).show();
    }

    @Override
    public void onTransactionCancel(String s, Bundle bundle) {
        dlg.dismiss();
        showSnackBar(this,"Transaction Cancelled");
//        Toast.makeText(this, s + bundle.toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
