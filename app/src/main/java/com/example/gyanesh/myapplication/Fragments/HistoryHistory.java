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
public class HistoryHistory extends Fragment {


    public HistoryHistory() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_history_history, container, false);


        String[] date2={"16-01-2000","25-01-2000","31-31-2031","5"};
        String [] progress2={"100","100","100","100"};
        String [] orderid2={"#0001","#0002","#0003","4"};
        ActiveOrdersAdapter activeOrdersAdapter2 = new ActiveOrdersAdapter(orderid2,progress2,date2);
        final RecyclerView recyclerView2 = (RecyclerView)view.findViewById(R.id.historyrecycler);
        recyclerView2.setAdapter(activeOrdersAdapter2);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getContext());
        recyclerView2.setLayoutManager(linearLayoutManager2);


        return view;
    }

}
