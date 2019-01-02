package com.example.gyanesh.myapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gyanesh.myapplication.Models.Garment;
import com.example.gyanesh.myapplication.utilClasses.AddressCardManager;
import com.example.gyanesh.myapplication.utilClasses.DateSelectManager;
import com.example.gyanesh.myapplication.utilClasses.PaytmManager;
import com.example.gyanesh.myapplication.utilClasses.SelectedClothesAdapter;
import com.example.gyanesh.myapplication.utilClasses.TejManager;
import com.parse.FunctionCallback;
import com.parse.ParseCloud;
import com.parse.ParseException;
import com.paytm.pgsdk.PaytmPaymentTransactionCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.example.gyanesh.myapplication.utilClasses.Constants.ADD_CLOTHES_REQUEST_CODE;
import static com.example.gyanesh.myapplication.utilClasses.Constants.SELECT_ADDRESS_REQUEST_CODE;
import static com.example.gyanesh.myapplication.utilClasses.Constants.TEZ_REQUEST_CODE;
import static com.example.gyanesh.myapplication.utilClasses.OrderManager.make_order;
import static com.example.gyanesh.myapplication.utilClasses.OrderManager.sendOrder;

public class PlaceOrderActivity extends AppCompatActivity implements PaytmPaymentTransactionCallback, AdapterView.OnItemSelectedListener {

    public int selectedAddress = -1;
    Map<Integer, Garment> selectedGarments;
    //TODO Initialize these values as user fills the details
//    private Address address = new Address();
    private int clothes = 10;
    private double cost = 50;
    private int payMode = 0;
    View v7;
    ProgressDialog dlg;
    HashMap<String, String> params = new HashMap<>();

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == TEZ_REQUEST_CODE) {
            //TODO process based on data in response
            Log.e("result", data.getStringExtra("Status"));
        }

        if (requestCode == ADD_CLOTHES_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                selectedGarments = (Map<Integer, Garment>) data.getSerializableExtra("selectedGarments");
                RecyclerView listView = findViewById(R.id.selectedList);
//                listView.setVisibility(View.VISIBLE);
                listView.setAdapter(new SelectedClothesAdapter(new ArrayList<>(selectedGarments.values())));
                listView.setLayoutManager(new LinearLayoutManager(this));
//                listView.setNestedScrollingEnabled(false);
            }
        }

        if (requestCode == SELECT_ADDRESS_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                selectedAddress = data.getIntExtra("selectedAddress", -1);
                updateAddressCard();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);
        v7 = findViewById(R.id.add_clothes);
        v7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AddClothesActivity.class);
                startActivityForResult(intent, ADD_CLOTHES_REQUEST_CODE);
            }
        });

        Toolbar toolbar;
        toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        androidx.appcompat.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        //FOr Address Selection
        updateAddressCard();

        //For handling date and time selection
        DateSelectManager dateSelectManager = new DateSelectManager(this);
        dateSelectManager.setColors();

        dlg = new ProgressDialog(this);
        Button confirm = findViewById(R.id.btn_confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                TODO Later after development set paymentDone to false
                Boolean paymentDone = false;
                //PayTm
                if (payMode == 1) {
                    PaytmManager paytmManager = new PaytmManager(PlaceOrderActivity.this);
                    paytmManager.paytm(params, dlg);
                    paymentDone = true;
                }
                //Tez
                else if (payMode == 0) {
                    TejManager tejManager = new TejManager(PlaceOrderActivity.this);
                    tejManager.tej();
                    paymentDone = true;
                }
                //Cash On Delivery
                else if (payMode == 2) {
                    paymentDone = true;
                }
                if (paymentDone) {
                    //TODO Check if all fields are correctly filled otherwise show Error
                    sendOrder(PlaceOrderActivity.this, make_order(selectedAddress, clothes, payMode, cost));
                }
            }
        });

        Spinner spinner = findViewById(R.id.spinner5);
        spinner.setOnItemSelectedListener(this);
        List<String> options = new ArrayList<String>();
        options.add("Tez");
        options.add("Paytm");
        options.add("Cash On Delivery");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, options);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

    }


    public void updateAddressCard() {
        if (selectedAddress != -1) {

            CardView cardView = findViewById(R.id.address_layout_order_activity);
            cardView.setVisibility(View.VISIBLE);

            AddressCardManager addressCardManager = new AddressCardManager(cardView);
            addressCardManager.updateDetailsInCard(selectedAddress);

            cardView = findViewById(R.id.gonewala);
            cardView.setVisibility(View.GONE);

            //TODO update this to edit the current address
            ImageView btn_edit = findViewById(R.id.btn_edit);
            btn_edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(PlaceOrderActivity.this, AddAddressActivity.class);
                    startActivityForResult(intent, SELECT_ADDRESS_REQUEST_CODE);
                }
            });
            btn_edit = findViewById(R.id.btn_delete);
            btn_edit.setVisibility(View.GONE);

        } else {
            CardView cardView = findViewById(R.id.address_layout_order_activity);
            cardView.setVisibility(View.GONE);
            TextView textView = findViewById(R.id.textView8);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(PlaceOrderActivity.this, AddAddressActivity.class);
                    startActivityForResult(intent, SELECT_ADDRESS_REQUEST_CODE);
                }
            });
        }

    }

    //1 for paytm
    //0 for Tez
    //2 for Cash on delivery
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        payMode = position;
        TextView mode = findViewById(R.id.textView25);
        String Mode = parent.getSelectedItem().toString();
        mode.setText(Mode);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        payMode = 2;
    }

    //all these overriden method is to detect the payment result accordingly
    @Override
    public void onTransactionResponse(Bundle bundle) {
        params.put("CHECKSUMHASH", bundle.getString("CHECKSUMHASH"));
        Log.e("MSG", params.toString());
        Log.e("Bundle", bundle.toString());

//        Toast.makeText(this,"Transaction Success", Toast.LENGTH_LONG).show();
        ParseCloud.callFunctionInBackground("verifyCheckSum", params, new FunctionCallback<Boolean>() {
            @Override
            public void done(Boolean ret, ParseException e) {
                dlg.dismiss();
                if (e == null) {
                    Log.e("verified CheckSum ", ret.toString());
                    dlg.dismiss();
                    Toast.makeText(PlaceOrderActivity.this, ret.toString(), Toast.LENGTH_LONG).show();
                } else {
                    Log.e("Error", e.toString());
                    Toast.makeText(PlaceOrderActivity.this, e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

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
}
