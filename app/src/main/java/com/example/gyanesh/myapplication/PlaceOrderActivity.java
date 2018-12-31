package com.example.gyanesh.myapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
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

import com.example.gyanesh.myapplication.Models.Address;
import com.example.gyanesh.myapplication.Models.Garment;
import com.example.gyanesh.myapplication.Models.OrderModel;
import com.example.gyanesh.myapplication.utilClasses.SelectedClothesAdapter;
import com.parse.FunctionCallback;
import com.parse.ParseCloud;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.paytm.pgsdk.PaytmOrder;
import com.paytm.pgsdk.PaytmPGService;
import com.paytm.pgsdk.PaytmPaymentTransactionCallback;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.example.gyanesh.myapplication.utilClasses.Constants.CALLBACK_URL;
import static com.example.gyanesh.myapplication.utilClasses.Constants.CHANNEL_ID;
import static com.example.gyanesh.myapplication.utilClasses.Constants.INDUSTRY_TYPE_ID;
import static com.example.gyanesh.myapplication.utilClasses.Constants.M_ID;
import static com.example.gyanesh.myapplication.utilClasses.Constants.WEBSITE;
import static com.example.gyanesh.myapplication.utilClasses.Constants.getDay;
import static com.example.gyanesh.myapplication.utilClasses.Constants.utilDate;

public class PlaceOrderActivity extends AppCompatActivity implements PaytmPaymentTransactionCallback, AdapterView.OnItemSelectedListener {

    private static final int TEZ_REQUEST_CODE = 123;
    private static final int ADD_CLOTHES_REQUEST_CODE = 124;
    public static int callme = -1;
    Map<Integer, Garment> selectedGarments;
    private static final String GOOGLE_TEZ_PACKAGE_NAME = "com.google.android.apps.nbu.paisa.user";
    //TODO Initialize these values as user fills the details
    private Address address = new Address();
    private Date c = Calendar.getInstance().getTime();
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    private int clothes = 10;
    private double cost = 50;
    private int payMode = 0;
    String selectedDate;
    String selectedSlot;
    Drawable colorv1, colorv4, colorv5, colorv6;
    View v1, v2, v3, v4, v5, v6, v7;
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


        TextView Name = findViewById(R.id.Name);
        TextView number = findViewById(R.id.al_number);
        TextView al_default = findViewById(R.id.al_default);
        TextView al_Add1 = findViewById(R.id.al_address);
        TextView al_Add2 = findViewById(R.id.al_address_2);
        TextView Al_city = findViewById(R.id.al_city);
        TextView Al_code = findViewById(R.id.al_code);

        if (callme != -1) {
            Name.setText(AddAddressActivity.adressAAA.get(callme).name);
            number.setText(AddAddressActivity.adressAAA.get(callme).number);
            al_Add1.setText(AddAddressActivity.adressAAA.get(callme).add1);
            al_Add2.setText(AddAddressActivity.adressAAA.get(callme).add2);
            Al_code.setText(AddAddressActivity.adressAAA.get(callme).pincode);
            Al_city.setText(AddAddressActivity.adressAAA.get(callme).city);
            al_default.setText(AddAddressActivity.adressAAA.get(callme).def_value);

        }


        ImageView imageView = findViewById(R.id.edit_address_icon);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlaceOrderActivity.this, AddAddressActivity.class);
                startActivity(intent);
            }
        });

        setColors();

        dlg = new ProgressDialog(this);

        Button confirm = findViewById(R.id.btn_confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                TODO Later after development set paymentDone to false
                Boolean paymentDone = false;
                //PayTm
                if (payMode == 1) {
                    paytm();
                    paymentDone = true;
                }
                //Tez
                else if (payMode == 0) {
                    Uri uri = new Uri.Builder()
                            .scheme("upi")
                            .authority("pay")
                            .appendQueryParameter("pa", "test@axisbank")
                            .appendQueryParameter("pn", "Test Merchant")
                            .appendQueryParameter("mc", "1234")
                            .appendQueryParameter("tr", "123456789")
                            .appendQueryParameter("tn", "test transaction note")
                            .appendQueryParameter("am", "1.00")
                            .appendQueryParameter("cu", "INR")
                            .appendQueryParameter("url", "https://test.merchant.website")
                            .build();

                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(uri);
                    intent.setPackage(GOOGLE_TEZ_PACKAGE_NAME);
                    startActivityForResult(intent, TEZ_REQUEST_CODE);

                    paymentDone = true;
                }
                //Cash On Delivery
                else if (payMode == 2) {
                    paymentDone = true;
                }
                if (paymentDone) {
                    //TODO Check if all fields are correctly filled otherwise show Error
                    send_order();
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

    //1 for paytm
    //0 for Tez
    //2 for Cash on delivery
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
        payMode=position;
        TextView mode=findViewById(R.id.textView25);
        String Mode=parent.getSelectedItem().toString();
        mode.setText(Mode);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        payMode = 2;
    }

    private String generateString() {
        String uuid = UUID.randomUUID().toString();
        return uuid.replaceAll("-", "");
    }

    private void paytm() {
        // Use this map to send parameters to your Cloud Code function
        // Just push the parameters you want into it
        //Map<String, String> params = new HashMap<>();
        String random = generateString();
        params.put("ORDER_ID", random);
        params.put("MID", M_ID);
        params.put("INDUSTRY_TYPE_ID", INDUSTRY_TYPE_ID);
        params.put("CHANNEL_ID", CHANNEL_ID);
        params.put("WEBSITE", WEBSITE);
        params.put("CALLBACK_URL", CALLBACK_URL + random);
//      TODO ADD "ORDER_ID" "CUST_ID" "TXN_AMOUNT" "EMAIL" "MOBILE_NO" in parameters
        random = generateString();
        params.put("CUST_ID", random);
        params.put("TXN_AMOUNT", "100.12");
        params.put("EMAIL", "username@emailprovider.com");
        params.put("MOBILE_NO", "7777777777");
        Log.e("Initial Param", params.toString());
        dlg.setTitle("Please, wait a moment.");
        dlg.setMessage("Redirecting to payment...");
        dlg.show();

//      This calls the function in the Cloud Code
        ParseCloud.callFunctionInBackground("genCheckSum", params, new FunctionCallback<HashMap<String, String>>() {
            @Override
            public void done(HashMap mapObject, ParseException e) {
                dlg.dismiss();
                if (e == null) {
                    Log.e("Generated CheckSum ", mapObject.toString());
                    //Toast.makeText(PlaceOrderActivity.this,mapObject.toString(), Toast.LENGTH_LONG).show();
                    initializePaytmPayment(mapObject);
                } else {
                    Toast.makeText(PlaceOrderActivity.this, e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void initializePaytmPayment(HashMap params) {

        //getting paytm service
        PaytmPGService Service = PaytmPGService.getStagingService();

        //use this when using for production
        //PaytmPGService Service = PaytmPGService.getProductionService();

        //creating a paytm order object using the hashmap
        PaytmOrder order = new PaytmOrder(params);

        //intializing the paytm service
        Service.initialize(order, null);

        //finally starting the payment transaction
        Service.startPaymentTransaction(this, true, true, this);

    }


    //all these overriden method is to detect the payment result accordingly
    @Override
    public void onTransactionResponse(Bundle bundle) {
        params.put("CHECKSUMHASH",bundle.getString("CHECKSUMHASH"));
        Log.e("MSG",params.toString());
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

    private void send_order() {
        final ProgressDialog dlg = new ProgressDialog(this);
        dlg.setTitle("Please, wait a moment.");
        dlg.setMessage("Placing Your Order...");
        dlg.show();
        OrderModel order = new OrderModel();
        order.setAddress(address.toString());
        order.setUserId(ParseUser.getCurrentUser().getObjectId());
        order.setClothes(clothes);
        order.setPayMode(payMode);
        order.setPickupTime(c);

        int orderId = 123;
        //TODO calculate order Id
        order.setOrderId(orderId);
        //TODO Calculate cost
        order.setCost(cost);

        order.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    dlg.dismiss();
                    Toast.makeText(PlaceOrderActivity.this, "Successfully placed order", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(PlaceOrderActivity.this, HistoryActivity.class));
                } else {
                    dlg.dismiss();
                    Log.e("Failed to create order", e.toString());
                }
            }
        });

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public void setColors() {
        final Date date = Calendar.getInstance().getTime();
        final Date default1 = utilDate(1);
        final Date default2 = utilDate(2);
        final Date default3 = utilDate(3);
        View slotLayout,timeLayout;
        slotLayout =findViewById(R.id.slot_layout);
        timeLayout = findViewById(R.id.time_layout);
        v1 = timeLayout.findViewById(R.id.date1);
        v2 = timeLayout.findViewById(R.id.date2);
        v3 = timeLayout.findViewById(R.id.date3);
        v4 = slotLayout.findViewById(R.id.date1);
        v5 = slotLayout.findViewById(R.id.date2);
        v6 = slotLayout.findViewById(R.id.date3);
        final Drawable colorAccent = getResources().getDrawable(R.drawable.edit_text_round_blue);
        final Drawable colorPrimary = getResources().getDrawable(R.drawable.edit_text_corner_round_grey);
        final Drawable grey = getResources().getDrawable(R.drawable.edit_text_corner_round_grey);

        //TODO Starpoint use view.onclickListener before setClickable false

        v1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setBackground(colorAccent);

                TextView temp = v.findViewById(R.id.date);
                selectedDate = temp.getText().toString();

                v2.setBackground(colorPrimary);
                v3.setBackground(colorPrimary);
                if (date.after(default1)) {
                    v4.setBackground(grey);
                    colorv4 = grey;
                    v4.setClickable(false);
                } else {
                    v4.setBackground(colorPrimary);
                    colorv4 = colorPrimary;
                    v4.setClickable(true);
                }
                if (date.after(default2)) {
                    v5.setBackground(grey);
                    colorv5 = grey;
                    v5.setClickable(false);
                } else {
                    v5.setBackground(colorPrimary);
                    colorv5 = colorPrimary;
                    v5.setClickable(true);
                }
            }
        });

        View.OnClickListener otherTwo = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView temp = v.findViewById(R.id.date);
                selectedDate = temp.getText().toString();
                v1.setBackground(colorv1);
                v2.setBackground(colorPrimary);
                v3.setBackground(colorPrimary);
                v.setBackground(colorAccent);
                v4.setBackground(colorPrimary);
                v5.setBackground(colorPrimary);
                v6.setBackground(colorPrimary);
                colorv4 = colorv5 = colorv6 = colorPrimary;
                v4.setClickable(true);
                v5.setClickable(true);
                v6.setClickable(true);
            }
        };
        v2.setOnClickListener(otherTwo);
        v3.setOnClickListener(otherTwo);


        View.OnClickListener slotListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v4.setBackground(colorv4);
                v5.setBackground(colorv5);
                v6.setBackground(colorv6);
                v.setBackground(colorAccent);
                TextView temp = v.findViewById(R.id.date);
                selectedSlot = temp.getText().toString();
                Toast.makeText(PlaceOrderActivity.this, selectedDate + "  Slot: " + selectedSlot, Toast.LENGTH_SHORT).show();
            }
        };
        v4.setOnClickListener(slotListener);
        v5.setOnClickListener(slotListener);
        v6.setOnClickListener(slotListener);

        if (date.after(default3)) {
            v1.setBackground(grey);
            colorv1 = grey;
            v1.setClickable(false);
            v4.setClickable(false);
            v5.setClickable(false);
            v6.setClickable(false);
        } else {
            colorv1 = colorPrimary;
            v1.setBackground(colorPrimary);
        }
        v2.setBackground(colorPrimary);
        v3.setBackground(colorPrimary);
        v4.setBackground(grey);
        v5.setBackground(grey);
        v6.setBackground(grey);


        SimpleDateFormat df1 = new SimpleDateFormat("dd - MMM");
        String formattedDate = df1.format(c);
        TextView textView1 = v1.findViewById(R.id.date);
        textView1.setText(formattedDate);
        Calendar cal = Calendar.getInstance();
        cal.setTime(c);
        cal.add(Calendar.DAY_OF_YEAR, 1);
        String formattedDate2 = df1.format(cal.getTime());
        int d1 = cal.get(Calendar.DAY_OF_WEEK);
        TextView day11 = v1.findViewById(R.id.day);
        day11.setText(getDay(d1));
        TextView textView2 = v2.findViewById(R.id.date);
        textView2.setText(formattedDate2);
        int d2 = (d1 + 1) % 7;
        TextView day22 = v2.findViewById(R.id.day);
        day22.setText(getDay(d2));
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(c);
        cal2.add(Calendar.DAY_OF_YEAR, 2);
        String formattedDate3 = df1.format(cal2.getTime());
        TextView textView3 = v3.findViewById(R.id.date);
        textView3.setText(formattedDate3);
        int d3 = (d1 + 2) % 7;
        TextView day33 = v3.findViewById(R.id.day);
        day33.setText(getDay(d3));


        TextView textView;
        textView = v4.findViewById(R.id.date);
        textView.setText("10 : 00");
        textView = v4.findViewById(R.id.day);
        textView.setText("A M");
        textView = v5.findViewById(R.id.date);
        textView.setText("2 : 00");
        textView = v5.findViewById(R.id.day);
        textView.setText("P M");
        textView = v6.findViewById(R.id.date);
        textView.setText("6 : 00");
        textView = v6.findViewById(R.id.day);
        textView.setText("P M");
    }
}
