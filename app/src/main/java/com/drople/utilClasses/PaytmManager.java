package com.drople.utilClasses;

import android.app.Activity;
import android.app.ProgressDialog;
import com.paytm.pgsdk.PaytmOrder;
import com.paytm.pgsdk.PaytmPGService;
import com.paytm.pgsdk.PaytmPaymentTransactionCallback;

import java.util.HashMap;

class PaytmManager {

    private Activity activity;
    PaytmManager(Activity activity){
        this.activity = activity;
    }

    void paytm(int total_amt) {
        final ProgressDialog dlg = new ProgressDialog(activity);
        HashMap<String, String> params = new HashMap<>();
//        String random = generateString();
//        params.put("ORDER_ID", random);
//        params.put("CUST_ID", ParseUser.getCurrentUser().getObjectId());
//        params.put("TXN_AMOUNT", String.valueOf(total_amt));
//        params.put("EMAIL", ParseUser.getCurrentUser().getEmail());

        dlg.setTitle("Please, wait a moment.");
        dlg.setMessage("Redirecting to payment...");
        dlg.show();

////      This calls the function in the Cloud Code
//        ParseCloud.callFunctionInBackground("genCheckSum", params, new FunctionCallback<HashMap<String, String>>() {
//            @Override
//            public void done(HashMap mapObject, ParseException e) {
//                dlg.dismiss();
//                if (e == null) {
//                    Log.e("Generated CheckSum ", mapObject.toString());
//                    //Toast.makeText(PlaceOrderActivity.this,mapObject.toString(), Toast.LENGTH_LONG).show();
//                    initializePaytmPayment(mapObject);
//                } else {
//                    Toast.makeText(activity, e.toString(), Toast.LENGTH_LONG).show();
//                }
//            }
//        });
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
