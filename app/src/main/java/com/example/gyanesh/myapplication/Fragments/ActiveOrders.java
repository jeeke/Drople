package com.example.gyanesh.myapplication.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gyanesh.myapplication.Adapters.ActiveOrdersAdapter;
import com.example.gyanesh.myapplication.Models.Order;
import com.example.gyanesh.myapplication.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ActiveOrders extends Fragment {


    List<Order> orders;
    public ActiveOrders() {
        // Required empty public constructor
    }

    public void setOrders(List<Order> orders){
        this.orders = orders;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.frag_active_orders, container, false);
        ActiveOrdersAdapter activeOrdersAdapter = new ActiveOrdersAdapter(orders);
        final RecyclerView recyclerView = view.findViewById(R.id.activerecycler);
        recyclerView.setAdapter(activeOrdersAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        return view;
    }

}
