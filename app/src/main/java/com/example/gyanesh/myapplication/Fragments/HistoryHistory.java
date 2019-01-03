package com.example.gyanesh.myapplication.Fragments;


import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gyanesh.myapplication.Adapters.ActiveOrdersAdapter;
import com.example.gyanesh.myapplication.Models.Address;
import com.example.gyanesh.myapplication.Models.Order;
import com.example.gyanesh.myapplication.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryHistory extends Fragment{


    List<Order> orders;
    public HistoryHistory() {
        // Required empty public constructor
    }

    public void setOrders(List<Order> orders){
        this.orders = orders;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.frag_completed_orders, container, false);
        ActiveOrdersAdapter activeOrdersAdapter2 = new ActiveOrdersAdapter(orders);
        final RecyclerView recyclerView2 = getView().findViewById(R.id.historyrecycler);
        recyclerView2.setAdapter(activeOrdersAdapter2);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getContext());
        recyclerView2.setLayoutManager(linearLayoutManager2);

        return view;
    }

}
