package com.example.gyanesh.myapplication.Fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.gyanesh.myapplication.Adapters.OrdersAdaptor;
import com.example.gyanesh.myapplication.Models.Address;
import com.example.gyanesh.myapplication.R;
import com.example.gyanesh.myapplication.utilClasses.BackgroundData;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

public class OrdersFrag extends Fragment implements BackgroundData.Listener {

    BackgroundData backgroundData;
    ProgressDialog dialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.frag_orders, container, false);
        backgroundData = new BackgroundData(this);
        dialog = new ProgressDialog(getContext());
        dialog.setTitle("Fetching Your Orders, Please Wait.....");
        dialog.show();
        return view;
    }

    @Override
    public void onAddressFetch(List<Address> addressList) {

    }

    @Override
    public void onAddressUpdate(Boolean error) {

    }

    @Override
    public void onOrdersFetch(Boolean error) {

        if(error){
            dialog.dismiss();
            OrdersAdaptor orderHistoryAdapter = new OrdersAdaptor(getChildFragmentManager(),backgroundData.getActiveOrders(),backgroundData.getCompletedOrders());
            ViewPager viewPager = getView().findViewById(R.id.viewpagerorders);
            viewPager.setAdapter(orderHistoryAdapter);

            TabLayout tabLayout = getView().findViewById(R.id.tablayoutorders);
            tabLayout.setupWithViewPager(viewPager);
        }else{
            //TODO Something error happened
        }

    }
}