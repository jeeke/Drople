package com.drople;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.drople.Models.Address;
import com.drople.Models.Garment;
import com.drople.utilClasses.DateSelectManager;
import com.drople.utilClasses.OrderManager;
import com.paytm.pgsdk.PaytmPaymentTransactionCallback;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.drople.utilClasses.Constants.ADD_CLOTHES_REQUEST_CODE;
import static com.drople.utilClasses.Constants.SELECT_ADDRESS_REQUEST_CODE;
import static com.drople.utilClasses.Constants.TEZ_REQUEST_CODE;

public class PlaceOrderActivity extends AppCompatActivity implements PaytmPaymentTransactionCallback, AdapterView.OnItemSelectedListener, OrderManager.Listener {

    //TODO Initialize these values as user fills the details
    //Todo restrict user to add only 10 addresses
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
                orderManager.setSelectedAddress((Address) data.getParcelableExtra(("selectedAddress")));
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
        addClothes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SelectClothesActivity.class);
                startActivityForResult(intent, ADD_CLOTHES_REQUEST_CODE);
            }
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
        dateSelectManager.setColors();

        dlg = new ProgressDialog(this);
        Button confirm = findViewById(R.id.btn_confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderManager.setSelectedDate(dateSelectManager.getSelectedDate());
                if (orderManager.isValid()) {
                    dlg = new ProgressDialog(PlaceOrderActivity.this);
                    dlg.setTitle("Placing Your Order");
                    dlg.show();
                    if (orderManager.redirectToPayment()) {
                        orderManager.verifyAndPlaceOrder(null);
                    }
                }
            }
        });

        Spinner spinner = findViewById(R.id.spinner5);
        spinner.setOnItemSelectedListener(this);
        List<String> options = new ArrayList<>();
        options.add("Tez");
        options.add("Paytm");
        options.add("Cash On Delivery");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, options);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

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
        orderManager.verifyAndPlaceOrder(bundle);
    }

    //Paytm Callbacks
    @Override
    public void networkNotAvailable() {
        dlg.dismiss();
        Toast.makeText(this, "Network error", Toast.LENGTH_LONG).show();
    }

    @Override
    public void clientAuthenticationFailed(String s) {
        dlg.dismiss();
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }

    @Override
    public void someUIErrorOccurred(String s) {
        dlg.dismiss();
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onErrorLoadingWebPage(int i, String s, String s1) {
        dlg.dismiss();
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressedCancelTransaction() {
        dlg.dismiss();
        Toast.makeText(this, "Back Pressed", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onTransactionCancel(String s, Bundle bundle) {
        dlg.dismiss();
        Toast.makeText(this, s + bundle.toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


    //Order Manager Listener Callbacks


    private void alertDisplayer() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("Order Not Verified")
                .setMessage("Something bad happened, Please try again.")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Todo On clicked Ok
                    }
                });
        AlertDialog ok = builder.create();
        ok.show();
    }
}