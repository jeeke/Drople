package com.example.gyanesh.myapplication.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gyanesh.myapplication.R;
import com.example.gyanesh.myapplication.RateListAdapter;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ServicesFrag extends Fragment {

    private int cnt = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.frag_services, container, false);
        cnt++;
        CardView card = view.findViewById(R.id.service_card1);
        setListener(card);
        card = view.findViewById(R.id.service_card2);
        setListener(card);
        card = view.findViewById(R.id.service_card3);
        setListener(card);
        card = view.findViewById(R.id.time_layout);
        setListener(card);

        return view;
    }

    public void setListener(CardView card) {

        String[] items = {"cloth", "kambal", "bed sheets", "rajai", "jacket", "shoes", "towel", "blazzer", "triple", "college bag", "tourist bag"};
        int[] cost = {10, 250, 15, 250, 150, 40, 5, 100, 250, 30, 50};
        final RecyclerView recyclerView = card.findViewById(R.id.rate_list);
        final TextView textView = card.findViewById(R.id.rate_list_heading);
        RateListAdapter adapter = new RateListAdapter(items, cost);
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        final TextView para = card.findViewById(R.id.para);
        final TextView btn_more = card.findViewById(R.id.btn_more);

        btn_more.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                if (cnt % 2 == 1) {
                    para.setMaxLines(10000);
                    btn_more.setText("View Less");
                    recyclerView.setVisibility(View.VISIBLE);
                    textView.setVisibility(View.VISIBLE);
                    cnt++;
                } else {
                    para.setMaxLines(2);
                    btn_more.setText("View More");
                    recyclerView.setVisibility(View.GONE);
                    textView.setVisibility(View.GONE);
                    cnt++;
                }
            }
        });

    }

}