package com.example.gyanesh.myapplication.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gyanesh.myapplication.Adapters.OrdersTabAdaptor;
import com.example.gyanesh.myapplication.R;
import com.google.android.material.tabs.TabLayout;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

public class OrdersFrag extends Fragment{


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.frag_orders, container, false);
        OrdersTabAdaptor orderHistoryAdapter = new OrdersTabAdaptor(getChildFragmentManager(),getContext());
        ViewPager viewPager = view.findViewById(R.id.viewpagerorders);
        viewPager.setAdapter(orderHistoryAdapter);
        TabLayout tabLayout = view.findViewById(R.id.tablayoutorders);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }

}