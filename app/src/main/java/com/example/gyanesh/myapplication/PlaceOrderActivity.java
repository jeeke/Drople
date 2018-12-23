package com.example.gyanesh.myapplication;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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

import com.example.gyanesh.myapplication.Models.OrderModel;
import com.parse.FunctionCallback;
import com.parse.ParseCloud;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.paytm.pgsdk.PaytmOrder;
import com.paytm.pgsdk.PaytmPGService;
import com.paytm.pgsdk.PaytmPaymentTransactionCallback;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import static com.example.gyanesh.myapplication.utilClasses.Constants.CALLBACK_URL;
import static com.example.gyanesh.myapplication.utilClasses.Constants.CHANNEL_ID;
import static com.example.gyanesh.myapplication.utilClasses.Constants.INDUSTRY_TYPE_ID;
import static com.example.gyanesh.myapplication.utilClasses.Constants.M_ID;
import static com.example.gyanesh.myapplication.utilClasses.Constants.WEBSITE;
import static com.example.gyanesh.myapplication.utilClasses.Constants.getDay;

public class PlaceOrderActivity extends AppCompatActivity implements PaytmPaymentTransactionCallback, AdapterView.OnItemSelectedListener {

    private  static final  int TEZ_REQUEST_CODE = 123;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == TEZ_REQUEST_CODE){
            //TODO process based on data in response
            Log.e("result",data.getStringExtra("Status"));
        }
    }

    private  static final  String GOOGLE_TEZ_PACKAGE_NAME = "com.google.android.apps.nbu.paisa.user";

    //TODO Initialize these values as user fills the details
    private String address = "gffgkgfhk";
    public static  int callme = 0; //none of your business.. ignore it ... but dont delete !!
    private Date c= Calendar.getInstance().getTime();
    SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
    private int clothes =10;
    private double cost = 50;
    private int payMode=-1;

    View v1,v2,v3,v4,v5,v6,v7;
    ProgressDialog dlg;

    HashMap<String, String> params = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);

        v7 = findViewById(R.id.add_clothes);
        v7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AddClothesActivity.class);
                startActivity(intent);
            }
        });

        Toolbar toolbar;
        toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        androidx.appcompat.app.ActionBar actionBar =  getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        TextView textViewn = findViewById(R.id.al_name);

        TextView textViewn2 = findViewById(R.id.al_number);
        TextView textViewn3 = findViewById(R.id.al_default);
        TextView textViewn4 = findViewById(R.id.al_address);
        TextView textViewn6 = findViewById(R.id.address_2);
        TextView textViewn5 = findViewById(R.id.al_city);
        TextView textViewn7 = findViewById(R.id.al_code);
        textViewn4.setText("Address Not Selected");
        textViewn5.setText("Edit address");
        Intent intent = getIntent();
        if(callme==1) {
            textViewn.setText(intent.getStringExtra("name"));
            textViewn2.setText(intent.getStringExtra("number"));
            textViewn4.setText(intent.getStringExtra("address1"));
            textViewn6.setText(intent.getStringExtra("address2"));
            textViewn5.setText(intent.getStringExtra("city"));
            textViewn7.setText(intent.getStringExtra("code"));
            String bho = intent.getStringExtra("checkbox");
            if(bho.equals("hello"))
            {
                textViewn3.setText("Default");
            }

        }callme = 0;





        ImageView imageView = (ImageView) findViewById(R.id.edit_address_icon);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlaceOrderActivity.this,AddressActivity.class);
                startActivity(intent);
            }
        });

        dlg= new ProgressDialog(this);
        Date date=Calendar.getInstance().getTime();
        DateFormat dateFormat=new SimpleDateFormat("hh:mm");
        String frmtDate=dateFormat.format(date);
        TextView textView;
        String default1="10:00";
        String default2="2:00";
        String default3="6:00";
        v1 = findViewById(R.id.date1);
        v2 = findViewById(R.id.date2);
        v3 = findViewById(R.id.date3);
        v4 = findViewById(R.id.day1);
        v5 = findViewById(R.id.day2);
        v6 = findViewById(R.id.day3);
        if(default1.compareTo(frmtDate)>=0)
        {
            v4.setBackgroundColor(getResources().getColor(R.color.grey));
        }
        else
        {
            v4.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        }
        if(default2.compareTo(frmtDate)>=0)
        {
            v5.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        }
        else
        {
            v5.setBackgroundColor(getResources().getColor(R.color.grey));
        }
        if(default3.compareTo(frmtDate)>=0)
        {
            v6.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        }
        else
        {
            v6.setBackgroundColor(getResources().getColor(R.color.grey));
        }
        v1.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        v2.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        v3.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

        SimpleDateFormat df1 = new SimpleDateFormat("dd - MMM");
        String formattedDate = df1.format(c);
        TextView textView1=v1.findViewById(R.id.date);
        textView1.setText(formattedDate);
        Calendar cal=Calendar.getInstance();
        cal.setTime(c);
        cal.add(Calendar.DAY_OF_YEAR,1);
        String formattedDate2=df1.format(cal.getTime());
        int d1=cal.get(Calendar.DAY_OF_WEEK);
        TextView day11=v1.findViewById(R.id.day);
        day11.setText(getDay(d1));
        TextView textView2=v2.findViewById(R.id.date);
        textView2.setText(formattedDate2);
        int d2=(d1+1)%7;
        TextView day22=v2.findViewById(R.id.day);
        day22.setText(getDay(d2));
        Calendar cal2=Calendar.getInstance();
        cal2.setTime(c);
        cal2.add(Calendar.DAY_OF_YEAR,2);
        String formattedDate3=df1.format(cal2.getTime());


        TextView textView3=v3.findViewById(R.id.date);
        textView3.setText(formattedDate3);
        int d3=(d1+2)%7;
        TextView day33=v3.findViewById(R.id.day);
        day33.setText(getDay(d3));
        textView= v4.findViewById(R.id.date);
        textView.setText("10 : 00");
        textView= v4.findViewById(R.id.day);
        textView.setText("A M");
        textView = v5.findViewById(R.id.date);
        textView.setText("2 : 00");
        textView = v5.findViewById(R.id.day);
        textView.setText("P M");
        textView = v6.findViewById(R.id.date);
        textView.setText("6 : 00");
        textView = v6.findViewById(R.id.day);
        textView.setText("P M");
//Iske baad ki bakchodi dekh lena
        Button confirm = findViewById(R.id.btn_confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                TODO Uncomment and integrate all payment methods
//                //TODO Later after development set paymentDone to false
                Boolean paymentDone = false;

                if(payMode==0) {
                    paytm();
                    paymentDone = true;
                }
                    //TODO redirect to payment and set paymentDone
                else if(payMode==1){
                    Uri uri = new Uri.Builder()
                            .scheme("upi")
                            .authority("pay")
                            .appendQueryParameter("pa", "test@axisbank")
                            .appendQueryParameter("pn","Test Merchant")
                            .appendQueryParameter("mc","1234")
                            .appendQueryParameter("tr","123456789")
                            .appendQueryParameter("tn","test transaction note")
                            .appendQueryParameter("am","1.00")
                            .appendQueryParameter("cu","INR")
                            .appendQueryParameter("url","https://test.merchant.website")
                            .build();

                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(uri);
                    intent.setPackage(GOOGLE_TEZ_PACKAGE_NAME);
                    startActivityForResult(intent,TEZ_REQUEST_CODE);

                    paymentDone = true;
                }
                //TODO GK : Add 3rd else if for cash on delivery here
                //and check  if all fields are correct.
                else if(payMode==2){
                    Toast.makeText(PlaceOrderActivity.this,"COD Selected", Toast.LENGTH_LONG).show();
                }
                if(paymentDone){
                    //TODO Check if all fields are correctly filled otherwise show Error
                    send_order();
                }
                Uri uri = new Uri.Builder()
                        .scheme("upi")
                        .authority("pay")
                        .appendQueryParameter("pa", "test@axisbank")
                        .appendQueryParameter("pn","Test Merchant")
                        .appendQueryParameter("mc","1234")
                        .appendQueryParameter("tr","123456789")
                        .appendQueryParameter("tn","test transaction note")
                        .appendQueryParameter("am","1.00")
                        .appendQueryParameter("cu","INR")
                        .appendQueryParameter("url","https://test.merchant.website")
                        .build();

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(uri);
                intent.setPackage(GOOGLE_TEZ_PACKAGE_NAME);
                startActivityForResult(intent,TEZ_REQUEST_CODE);
            }
        });
        Spinner spinner=(Spinner) findViewById(R.id.spinner5);
        spinner.setOnItemSelectedListener(this);
        List<String> options=new ArrayList<String>();
        options.add("Paytm");
        options.add("Tez");
        options.add("Cash On Delivery");
        ArrayAdapter<String> dataAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,options);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

    }
    //Return OnItemSelected item from here.. to redirect payment
    //0 for paytm
    //1 for Tez
    //2 for Cash on delivery
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
        payMode=position;
        Toast.makeText(PlaceOrderActivity.this,"This selected", Toast.LENGTH_LONG).show();

    }
//GK : Tou will get position from here
    @Override
    public void onNothingSelected(AdapterView<?> parent) {


    }

    private String generateString() {
        String uuid = UUID.randomUUID().toString();
        return uuid.replaceAll("-", "");
    }

    public void testing(View view)
    {
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }
    private void paytm(){

        // Use this map to send parameters to your Cloud Code function
        // Just push the parameters you want into it
        //Map<String, String> params = new HashMap<>();
        String random = generateString();
        params.put("ORDER_ID",random);
        params.put("MID", M_ID);
        params.put("INDUSTRY_TYPE_ID",INDUSTRY_TYPE_ID);
        params.put("CHANNEL_ID",CHANNEL_ID);
        params.put("WEBSITE",WEBSITE);
        params.put("CALLBACK_URL",CALLBACK_URL + random);
//      TODO ADD "ORDER_ID" "CUST_ID" "TXN_AMOUNT" "EMAIL" "MOBILE_NO" in parameters
        random = generateString();
        params.put("CUST_ID",random);
        params.put("TXN_AMOUNT","100.12");
        params.put("EMAIL","username@emailprovider.com");
        params.put("MOBILE_NO","7777777777");
        Log.e("Initial Param",params.toString());
        dlg.setTitle("Please, wait a moment.");
        dlg.setMessage("Redirecting to payment...");
        dlg.show();

//      This calls the function in the Cloud Code
        ParseCloud.callFunctionInBackground("genCheckSum", params, new FunctionCallback<HashMap<String,String>>() {
            @Override
            public void done(HashMap mapObject, ParseException e) {
                dlg.dismiss();
                if (e == null) {
                    Log.e("Generated CheckSum ",mapObject.toString());
                    //Toast.makeText(PlaceOrderActivity.this,mapObject.toString(), Toast.LENGTH_LONG).show();
                    initializePaytmPayment(mapObject);
                }
                else {
                    Toast.makeText(PlaceOrderActivity.this,e.toString() , Toast.LENGTH_LONG).show();
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
                    Log.e("verified CheckSum ",ret.toString());
                    dlg.dismiss();
                    Toast.makeText(PlaceOrderActivity.this,ret.toString(), Toast.LENGTH_LONG).show();
                }
                else {
                    Log.e("Error",e.toString());
                    Toast.makeText(PlaceOrderActivity.this,e.toString() , Toast.LENGTH_LONG).show();
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


    public void callBack(View view){
        switch (view.getId()){
            case R.id.date1:
                v1.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                v2.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                v3.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                break;
            case R.id.date2:
                v1.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                v2.setBackgroundColor(getResources().getColor(R.color.colorAccent));
               v3.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                break;
            case R.id.date3:
                v1.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                v2.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                v3.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                break;
            case R.id.day1:
                v4.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                v5.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                v6.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                break;
            case R.id.day2:
                v4.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                v5.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                v6.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                break;
            default:
                v4.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                v5.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                v6.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        }
    }

    private void send_order() {
        final ProgressDialog dlg = new ProgressDialog(this);
        dlg.setTitle("Please, wait a moment.");
        dlg.setMessage("Placing Your Order...");
        dlg.show();
        OrderModel order = new OrderModel();
        order.setAddress(address);
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
}
