package com.example.gyanesh.myapplication.utilClasses;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.gyanesh.myapplication.Models.Address;
import com.example.gyanesh.myapplication.Models.Garment;
import com.example.gyanesh.myapplication.R;
import com.example.gyanesh.myapplication.SelectAddressActivity;
import com.parse.FunctionCallback;
import com.parse.ParseCloud;
import com.parse.ParseException;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import androidx.cardview.widget.CardView;

import static com.example.gyanesh.myapplication.utilClasses.CloudDbHelper.garmentToHashMap;
import static com.example.gyanesh.myapplication.utilClasses.Constants.ADDRESS;
import static com.example.gyanesh.myapplication.utilClasses.Constants.PAY_MODE;
import static com.example.gyanesh.myapplication.utilClasses.Constants.PICKUP_TIME;
import static com.example.gyanesh.myapplication.utilClasses.Constants.SELECT_ADDRESS_REQUEST_CODE;
import static com.example.gyanesh.myapplication.utilClasses.Constants.TOTAL_COST;
import static com.example.gyanesh.myapplication.utilClasses.Constants.USER_ID;

public class OrderManager {
    //    private Order order;
    private Address selectedAddress;
    private ArrayList<Garment> selectedGarments;
    private int total_amount;
    private int payMode;
    private String selectedDate;

    public void setSelectedAddress(Address selectedAddress) {
        this.selectedAddress = selectedAddress;
    }

    public void setSelectedGarments(ArrayList<Garment> selectedGarments) {
        this.selectedGarments = selectedGarments;
    }

    public void setTotal_amount(int total_amount) {
        this.total_amount = total_amount;
    }

    public void setPayMode(int payMode) {
        this.payMode = payMode;
    }

    public void setSelectedDate(String selectedDate) {
        this.selectedDate = selectedDate;
    }

    public OrderManager(Listener listener) {
        this.listener = listener;
    }

    public interface Listener {
    }

    private Listener listener;

    public boolean isValid() {
        if (selectedAddress == null) {
            Toast.makeText((Context) listener, "Please Select An Address", Toast.LENGTH_SHORT).show();
            return false;
        } else if (selectedDate == null) {
            Toast.makeText((Context) listener, "Please Select Pickup Time", Toast.LENGTH_SHORT).show();
            return false;
        } else if (selectedGarments == null) {
            Toast.makeText((Context) listener, "Please Select Clothes", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    public void verifyAndPlaceOrder(Bundle bundle) {
        final ProgressDialog dialog = new ProgressDialog((Context) listener);
        dialog.setTitle("Placing Your Order, Please Wait....");
        dialog.show();

        //Paytm Bundle
        HashMap<String,Object> params = new HashMap<>();
        if(bundle!=null){
            Log.e("Paytm Bundle",bundle.toString());
            params.put("paytm",bundleToMap(bundle));
        }
        //Order Details
        params.put(ADDRESS,selectedAddress.toString());
        params.put(PAY_MODE,payMode);
        //TODO update this to get selected time
        params.put(PICKUP_TIME,Calendar.getInstance().getTime());
        params.put(USER_ID,ParseUser.getCurrentUser().getObjectId());
        params.put(TOTAL_COST,total_amount);

        //Verification Details
        HashMap<String, Object> verify = garmentToHashMap(selectedGarments);
        params.put("verify", verify);

        ParseCloud.callFunctionInBackground("verify", params, new FunctionCallback<String>() {
            @Override
            public void done(String ret, ParseException e) {
                dialog.dismiss();
                if (e==null) {
                    Log.e("Error",ret);
                    Toast.makeText((Context) listener, "Successfully placed order", Toast.LENGTH_SHORT).show();
                } else {
                    Log.e("Error", e.toString());
                    Toast.makeText((Context) listener, "Order couldn't be placed", Toast.LENGTH_LONG).show();
                }
            }
        });
        //Todo something with this to verify the transaction status
    }


    private Map<String,String> bundleToMap(Bundle bundle){
        Map<String,String> map = new HashMap<>();
        Set<String> keys = bundle.keySet();
        for(String key:keys){
            map.put(key,bundle.getString(key));
        }
        return map;
    }


    public boolean redirectToPayment() {
        //Tez
        if (payMode == 0) {
            TejManager tejManager = new TejManager((Activity) listener);
            tejManager.tej(total_amount);
            return false;
        }
        //PayTm
        else if (payMode == 1) {
            PaytmManager paytmManager = new PaytmManager((Activity) listener);
            paytmManager.paytm(total_amount);
            return false;
        }
        //Cash On Delivery
        return true;
    }

    public SelectedClothesAdapter getSelectClothesAdapter() {
        return new SelectedClothesAdapter(selectedGarments);
    }

    public void updateAddressCard() {
        final Activity activity = (Activity) listener;
        if (selectedAddress != null) {
            CardView cardView = activity.findViewById(R.id.address_layout_order_activity);
            cardView.setVisibility(View.VISIBLE);

            AddressCardManager addressCardManager = new AddressCardManager(cardView, selectedAddress);
            addressCardManager.updateDetailsInCard();

            cardView = activity.findViewById(R.id.gonewala);
            cardView.setVisibility(View.GONE);

            //TODO update this to edit the current address
            ImageView btn_edit = activity.findViewById(R.id.btn_edit);
            btn_edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(activity, SelectAddressActivity.class);
                    activity.startActivityForResult(intent, SELECT_ADDRESS_REQUEST_CODE);
                }
            });
            btn_edit = activity.findViewById(R.id.btn_delete);
            btn_edit.setVisibility(View.GONE);

        } else {
            CardView cardView = activity.findViewById(R.id.address_layout_order_activity);
            cardView.setVisibility(View.GONE);
            View view = activity.findViewById(R.id.gonewala);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(activity, SelectAddressActivity.class);
                    activity.startActivityForResult(intent, SELECT_ADDRESS_REQUEST_CODE);
                }
            });
        }

    }
}
