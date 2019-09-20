package com.drople.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.drople.Adapters.OrderDetailAdapter;
import com.drople.Models.Order;
import com.drople.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ActiveOrders extends Fragment {
    public static List<Order> orders;

    public ActiveOrders() {
    }

    public static OrderDetailAdapter orderDetailAdapter;

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.frag_active_orders, container, false);
        orderDetailAdapter = new OrderDetailAdapter(orders);
        final RecyclerView recyclerView = view.findViewById(R.id.activerecycler);
        recyclerView.setAdapter(orderDetailAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        return view;
    }

}
