package com.example.gyanesh.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.gyanesh.myapplication.utilClasses.Constants.CALLBACK_URL;
import static com.example.gyanesh.myapplication.utilClasses.Constants.CHANNEL_ID;
import static com.example.gyanesh.myapplication.utilClasses.Constants.INDUSTRY_TYPE_ID;
import static com.example.gyanesh.myapplication.utilClasses.Constants.M_ID;
import static com.example.gyanesh.myapplication.utilClasses.Constants.WEBSITE;

public class PlaceOrderActivity extends AppCompatActivity implements PaytmPaymentTransactionCallback {

    //TODO Initialize these values as user fills the details
    private String address = "gffgkgfhk";
    private Date time = Calendar.getInstance().getTime();
    private int clothes =10;
    private double cost = 50;
    private int payMode = 1;

    View v1,v2,v3,v4,v5,v6;
    ProgressDialog dlg;

    HashMap<String, String> params = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);
        dlg= new ProgressDialog(this);
        TextView textView;
        v1 = findViewById(R.id.date1);
        v2 = findViewById(R.id.date2);
        v3 = findViewById(R.id.date3);
        v4 = findViewById(R.id.day1);
        v5 = findViewById(R.id.day2);
        v6 = findViewById(R.id.day3);
        v1.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        v2.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        v3.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        v4.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        v5.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        v6.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

//        TODO format date and set to textviews
//       Date c = Calendar.getInstance().getTime();
//        System.out.println("Current time => " + c);
//
//        SimpleDateFormat df = new SimpleDateFormat("dd - MMM");
//        String formattedDate = df.format(c);


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

        Button confirm = findViewById(R.id.btn_confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO Later after development set paymentDone to false
                Boolean paymentDone = false;

                if(payMode==1){
                    paytm();
                    //TODO redirect to payment and set paymentDone
                }else{
                    paymentDone = true;
                }

                if(paymentDone){
                    //TODO Check if all fields are correctly filled otherwise show Error
                    send_order();
                }
            }
        });
    }

    private String generateString() {
        String uuid = UUID.randomUUID().toString();
        return uuid.replaceAll("-", "");
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
                v1.findViewById(R.id.view).setBackgroundColor(getResources().getColor(R.color.colorAccent));
                v2.findViewById(R.id.view).setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                v3.findViewById(R.id.view).setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                break;
            case R.id.date2:
                v1.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                v2.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                v3.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                v1.findViewById(R.id.view).setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                v2.findViewById(R.id.view).setBackgroundColor(getResources().getColor(R.color.colorAccent));
                v3.findViewById(R.id.view).setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                break;
            case R.id.date3:
                v1.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                v2.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                v3.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                v1.findViewById(R.id.view).setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                v2.findViewById(R.id.view).setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                v3.findViewById(R.id.view).setBackgroundColor(getResources().getColor(R.color.colorAccent));
                break;
            case R.id.day1:
                v4.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                v5.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                v6.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                v4.findViewById(R.id.view).setBackgroundColor(getResources().getColor(R.color.colorAccent));
                v5.findViewById(R.id.view).setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                v6.findViewById(R.id.view).setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                break;
            case R.id.day2:
                v4.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                v5.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                v6.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                v4.findViewById(R.id.view).setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                v5.findViewById(R.id.view).setBackgroundColor(getResources().getColor(R.color.colorAccent));
                v6.findViewById(R.id.view).setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                break;
            default:
                v4.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                v5.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                v6.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                v4.findViewById(R.id.view).setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                v5.findViewById(R.id.view).setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                v6.findViewById(R.id.view).setBackgroundColor(getResources().getColor(R.color.colorAccent));
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
        order.setPickupTime(time);

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
}
