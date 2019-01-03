package com.example.gyanesh.myapplication.Fragments;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gyanesh.myapplication.Adapters.ActiveOrdersAdapter;
import com.example.gyanesh.myapplication.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ActiveOrders extends Fragment {


    public ActiveOrders() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_active_orders, container, false);



        String[] date={"16-01-2000","25-01-2000","31-31-2031","5-09-2000"};
        String [] progress={"25","30","90","60"};
        String [] orderid={"#0001","#0002","#0003","#0004"};
        ActiveOrdersAdapter activeOrdersAdapter = new ActiveOrdersAdapter(orderid,progress,date);
        final RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.activerecycler);
        recyclerView.setAdapter(activeOrdersAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        return view;
    }

}
