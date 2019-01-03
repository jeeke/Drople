package com.example.gyanesh.myapplication.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import com.example.gyanesh.myapplication.Adapters.ActiveOrdersAdapter;
import com.example.gyanesh.myapplication.Adapters.OrderHistoryAdapter;
import com.example.gyanesh.myapplication.Adapters.OrdersAdaptor;
import com.example.gyanesh.myapplication.Models.OrderModel;
import com.example.gyanesh.myapplication.R;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

public class OrdersFrag extends Fragment {

    //private int cnt = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.frag_orders_new, container, false);

        OrdersAdaptor orderHistoryAdapter = new OrdersAdaptor(getChildFragmentManager());
        ViewPager viewPager = view.findViewById(R.id.viewpagerorders);
        viewPager.setAdapter(orderHistoryAdapter);

        TabLayout tabLayout = view.findViewById(R.id.tablayoutorders);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }

}