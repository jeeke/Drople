package com.example.gyanesh.myapplication.Fragments;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.gyanesh.myapplication.R;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class ServicesFrag extends Fragment {

    private int cnt = 0;

    public ServicesFrag() {
        // Required empty public constructor
    }


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
        card = view.findViewById(R.id.service_card4);
        setListener(card);
        return view;
    }

    public void setListener(CardView card) {

        final TextView para = card.findViewById(R.id.para);
        final TextView btn_more = card.findViewById(R.id.btn_more);

        btn_more.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                if (cnt % 2 == 1) {
                    para.setMaxLines(100);
                    btn_more.setText("View Less");
                    cnt++;
                } else {
                    para.setMaxLines(2);
                    btn_more.setText("View More");
                    cnt++;
                }
            }
        });

    }

}
