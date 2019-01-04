package com.example.gyanesh.myapplication.utilClasses;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.gyanesh.myapplication.Models.Address;
import com.example.gyanesh.myapplication.Models.Garment;
import com.example.gyanesh.myapplication.Models.Order;
import com.example.gyanesh.myapplication.R;
import com.example.gyanesh.myapplication.SelectAddressActivity;
import com.parse.FunctionCallback;
import com.parse.ParseCloud;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.HashMap;

import androidx.cardview.widget.CardView;

import static com.example.gyanesh.myapplication.utilClasses.CloudDbHelper.garmentToHashMap;
import static com.example.gyanesh.myapplication.utilClasses.Constants.SELECT_ADDRESS_REQUEST_CODE;
import static com.example.gyanesh.myapplication.utilClasses.Constants.generateString;

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
        void onOrderVerified(boolean match);

        void onError(boolean err);
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

    public void verifyOrder() {
        HashMap<String, Object> params = garmentToHashMap(selectedGarments);
        params.put("amount", total_amount);
        ParseCloud.callFunctionInBackground("verify", params, new FunctionCallback<Boolean>() {
            @Override
            public void done(Boolean match, ParseException e) {
                if (e == null) {
                    listener.onOrderVerified(match);
                } else {
                    listener.onError(true);
                    Log.e("Error 404", e.toString());
                    //todo accordingly
                }
            }
        });
    }

    public void sendOrder() {
        //Todo something with this to verify the transaction status
        final ProgressDialog dlg = new ProgressDialog((Context) listener);
        dlg.setTitle("Please, wait a moment.");
        dlg.setMessage("Placing Your Order...");
        dlg.show();
        Order order = new Order();
        order.setAddress(selectedAddress.toString());
        order.setUserId(ParseUser.getCurrentUser().getObjectId());
        order.setClothes(selectedGarments.toString());
        order.setPayMode(payMode);
        order.setPickupTime(selectedDate);
        //TODO calculate order Id
        order.setOrderId(generateString());
        //TODO Calculate cost
        order.setCost(total_amount);
        order.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    dlg.dismiss();
                    Toast.makeText((Context) listener, "Successfully placed order", Toast.LENGTH_SHORT).show();
                } else {
                    dlg.dismiss();
                    Log.e("Failed to create order", e.toString());
                }
            }
        });
    }


    public boolean redirectToPayment() {
//        Todo proceed to payment
//        TODO Later after development set paymentDone to false
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
