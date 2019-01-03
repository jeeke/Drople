package com.example.gyanesh.myapplication.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gyanesh.myapplication.Adapters.OrderDetailAdapter;
import com.example.gyanesh.myapplication.Models.Order;
import com.example.gyanesh.myapplication.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CompletedOrders extends Fragment {


    private List<Order> orders;
    public static OrderDetailAdapter completedOrdersAdapter;

    public CompletedOrders() {
        // Required empty public constructor
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.frag_completed_orders, container, false);
        completedOrdersAdapter = new OrderDetailAdapter(orders);
        final RecyclerView recyclerView2 = view.findViewById(R.id.historyrecycler);
        recyclerView2.setAdapter(completedOrdersAdapter);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getContext());
        recyclerView2.setLayoutManager(linearLayoutManager2);

        return view;
    }

}
