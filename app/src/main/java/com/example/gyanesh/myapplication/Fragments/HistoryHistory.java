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

import static com.example.gyanesh.myapplication.utilClasses.BackgroundData.getCompletedOrders;

/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryHistory extends Fragment {


    public HistoryHistory() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.frag_completed_orders, container, false);


        ActiveOrdersAdapter activeOrdersAdapter2 = new ActiveOrdersAdapter(getCompletedOrders());
        final RecyclerView recyclerView2 = view.findViewById(R.id.historyrecycler);
        recyclerView2.setAdapter(activeOrdersAdapter2);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getContext());
        recyclerView2.setLayoutManager(linearLayoutManager2);


        return view;
    }

}
