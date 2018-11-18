package com.example.gyanesh.myapplication.Fragments;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gyanesh.myapplication.DonateActivity;
import com.example.gyanesh.myapplication.PlaceOrderActivity;
import com.example.gyanesh.myapplication.R;
import com.example.gyanesh.myapplication.TrackOrderActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFrag extends Fragment {

    public HomeFrag() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.frag_home, container, false);
//        Attaching Listeners to Home buttons

//        donate clothes
        View home_button1 = layout.findViewById(R.id.constraintLayout2);
        home_button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),DonateActivity.class);
                startActivity(intent);
            }
        });

//        track order
        View home_button2 = layout.findViewById(R.id.constraintLayout3);
        home_button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),TrackOrderActivity.class);
                startActivity(intent);
            }
        });

//        place order
        View home_button3 = layout.findViewById(R.id.constraintLayout4);
        home_button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),PlaceOrderActivity.class);
                startActivity(intent);
            }
        });

//        Order History

        View home_button4 = layout.findViewById(R.id.constraintLayout5);
        home_button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),PlaceOrderActivity.class);
                startActivity(intent);
            }
        });
        return layout;
    }



}
