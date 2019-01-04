package com.example.gyanesh.myapplication.utilClasses;

import android.app.Activity;
import android.app.ProgressDialog;
import android.util.Log;
import android.widget.Toast;

import com.parse.FunctionCallback;
import com.parse.ParseCloud;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.paytm.pgsdk.PaytmOrder;
import com.paytm.pgsdk.PaytmPGService;
import com.paytm.pgsdk.PaytmPaymentTransactionCallback;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static com.example.gyanesh.myapplication.utilClasses.Constants.CALLBACK_URL;
import static com.example.gyanesh.myapplication.utilClasses.Constants.CHANNEL_ID;
import static com.example.gyanesh.myapplication.utilClasses.Constants.INDUSTRY_TYPE_ID;
import static com.example.gyanesh.myapplication.utilClasses.Constants.M_ID;
import static com.example.gyanesh.myapplication.utilClasses.Constants.WEBSITE;
import static com.example.gyanesh.myapplication.utilClasses.Constants.generateString;

class PaytmManager {

    private Activity activity;
    PaytmManager(Activity activity){
        this.activity = activity;
    }

    void paytm(int total_amt) {
        final ProgressDialog dlg = new ProgressDialog(activity);
        HashMap<String, String> params = new HashMap<>();
        String random = generateString();

        params.put("ORDER_ID", random);
        params.put("MID", M_ID);
        params.put("INDUSTRY_TYPE_ID", INDUSTRY_TYPE_ID);
        params.put("CHANNEL_ID", CHANNEL_ID);
        params.put("WEBSITE", WEBSITE);
        params.put("CALLBACK_URL", CALLBACK_URL + random);

//      TODO ADD "ORDER_ID" "CUST_ID" "TXN_AMOUNT" "EMAIL" "MOBILE_NO" in parameters
        params.put("CUST_ID", ParseUser.getCurrentUser().getObjectId());
        params.put("TXN_AMOUNT", String.valueOf(total_amt));
        params.put("EMAIL", ParseUser.getCurrentUser().getEmail());
//        params.put("MOBILE_NO", "7777777777");

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
                    Toast.makeText(activity, e.toString(), Toast.LENGTH_LONG).show();
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
        Service.startPaymentTransaction(activity, true, true, (PaytmPaymentTransactionCallback) activity);

    }

}
