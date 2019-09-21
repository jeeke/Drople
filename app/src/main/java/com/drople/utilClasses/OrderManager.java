package com.drople.utilClasses;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.drople.Models.Address;
import com.drople.Models.Garment;
import com.drople.Models.Order;
import com.drople.R;
import com.drople.SelectAddressActivity;
import com.drople.Server;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import androidx.cardview.widget.CardView;

import static com.drople.utilClasses.Constants.ADDRESS;
import static com.drople.utilClasses.Constants.PAY_MODE;
import static com.drople.utilClasses.Constants.PICKUP_TIME;
import static com.drople.utilClasses.Constants.SELECT_ADDRESS_REQUEST_CODE;

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
        } else if (selectedGarments != null) {
            Toast.makeText((Context) listener, "Please Select Clothes", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    public void verifyAndPlaceOrder(Bundle bundle, Server server) {
//        final ProgressDialog dialog = new ProgressDialog((Context) listener);
//        dialog.setTitle("Placing Your Order, Please Wait....");
//        dialog.show();
        Order order = new Order();
        order.garments = selectedGarments;
        order.cost = total_amount+"";
        order.pay_mode = payMode+"";
        order.pickup_time = selectedDate;
        order.address = selectedAddress;
        order.uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        if(bundle!=null){
            order.paytm = bundleToMap(bundle);
            Log.e("Paytm Bundle",bundle.toString());
        }
        server.placeOrder(order);
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
        if (payMode == 1) {
            TejManager tejManager = new TejManager((Activity) listener);
            tejManager.tej(total_amount);
            return false;
        }
        //PayTm
        else if (payMode == 2) {
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

//            AddressCardManager addressCardManager = new AddressCardManager(cardView, selectedAddress);
//            addressCardManager.updateDetailsInCard();
            TextView name, number, hostel, room, type;
            ImageView del;
            del = cardView.findViewById(R.id.btn_delete);
            name = cardView.findViewById(R.id.name);
            number = cardView.findViewById(R.id.number);
            type = cardView.findViewById(R.id.type);
            hostel = cardView.findViewById(R.id.hostel);
            room = cardView.findViewById(R.id.room);


            name.setText(selectedAddress.name);
            number.setText("Phone :  " + selectedAddress.phone);
            type.setText(selectedAddress.type);
            hostel.setText("Hostel :  " + selectedAddress.hostel);
            room.setText("Room No :  " + selectedAddress.room);

            del.setImageDrawable(cardView.getContext().getResources().getDrawable(R.drawable.ic_edit));
//
            cardView = activity.findViewById(R.id.gonewala);
            cardView.setVisibility(View.GONE);
            del.setOnClickListener(v -> {
                Intent intent = new Intent(activity, SelectAddressActivity.class);
                activity.startActivityForResult(intent, SELECT_ADDRESS_REQUEST_CODE);
            });
//
//            //TODO update this to edit the current address
//            ImageView btn_edit = activity.findViewById(R.id.btn_edit);
//
//            btn_edit = activity.findViewById(R.id.btn_delete);
//            btn_edit.setVisibility(View.GONE);

        } else {
            CardView cardView = activity.findViewById(R.id.address_layout_order_activity);
            cardView.setVisibility(View.GONE);
            View view = activity.findViewById(R.id.gonewala);
            view.setOnClickListener(v -> {
                Intent intent = new Intent(activity, SelectAddressActivity.class);
                activity.startActivityForResult(intent, SELECT_ADDRESS_REQUEST_CODE);
            });
        }

    }
}
