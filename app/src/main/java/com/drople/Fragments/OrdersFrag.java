package com.drople.Fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.drople.Adapters.AddAddressAdapter;
import com.drople.Adapters.OrdersTabAdaptor;
import com.drople.Models.Address;
import com.drople.Models.Order;
import com.drople.R;
import com.drople.SelectAddressActivity;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

public class OrdersFrag extends MainActivityFragments{

    ArrayList<Order> mActiceOrders,mCompletedOrders;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.frag_orders, container, false);
        toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        setHasOptionsMenu(true);
        mActiceOrders = new ArrayList<>();
        mCompletedOrders = new ArrayList<>();
        ProgressDialog dialog = new ProgressDialog(getContext());
        dialog.setTitle("Fetching your orders, Please Wait.....");
        dialog.show();
        FirebaseDatabase.getInstance().
                getReference().child("PrevOrders").child(FirebaseAuth.getInstance().
                getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot d : dataSnapshot.getChildren()) {
                    Order order = d.getValue(Order.class);
                    order.id = order.c_date.substring(8);
                    if(order.status.equals("3")){
                        mCompletedOrders.add(order);
                    }else mActiceOrders.add(order);
                }

                OrdersTabAdaptor orderHistoryAdapter = new OrdersTabAdaptor(getChildFragmentManager(),mActiceOrders,mCompletedOrders);
                ViewPager viewPager = view.findViewById(R.id.viewpagerorders);
                viewPager.setAdapter(orderHistoryAdapter);
                TabLayout tabLayout = view.findViewById(R.id.tablayoutorders);
                tabLayout.setupWithViewPager(viewPager);
                dialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                dialog.dismiss();
            }
        });

        return view;
    }

}