package com.example.gyanesh.myapplication.Fragments;


import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;


import com.example.gyanesh.myapplication.R;

import androidx.constraintlayout.widget.ConstraintLayout;

/**
 * A simple {@link Fragment} subclass.
 */
public class frag_support extends Fragment {


    public frag_support() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view =  inflater.inflate(R.layout.frag_support_us, container, false);
        ConstraintLayout constraintLayoutus = view.findViewById(R.id.constraint_about_us);
        ConstraintLayout constraintLayoutcon = view.findViewById(R.id.constraint_contact_us);
        ConstraintLayout constraintLayoutfaq = view.findViewById(R.id.constraint_Faqs);
        ConstraintLayout constraintLayoutop = view.findViewById(R.id.constraint_our_policies);
        final ScrollView scrollView = view.findViewById(R.id.scrollviewsupport);

        constraintLayoutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CardView cardView = view.findViewById(R.id.aboutus);
                if(cardView.getVisibility()==View.GONE)
                {
                    cardView.setVisibility(View.VISIBLE);
                }
                else
                {
                    cardView.setVisibility(View.GONE);
                }


            }
        });


        constraintLayoutcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CardView cardView = view.findViewById(R.id.contactus);
                if(cardView.getVisibility()==View.GONE)
                {
                    cardView.setVisibility(View.VISIBLE);
                }
                else
                {
                    cardView.setVisibility(View.GONE);
                }


            }
        });

        constraintLayoutop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CardView cardView = view.findViewById(R.id.ourpolicies);
                if(cardView.getVisibility()==View.GONE)
                {
                    cardView.setVisibility(View.VISIBLE);
                }
                else
                {
                    cardView.setVisibility(View.GONE);
                }


            }
        });

        constraintLayoutfaq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CardView cardView = view.findViewById(R.id.faqs);
                if(cardView.getVisibility()==View.GONE)
                {
                    cardView.setVisibility(View.VISIBLE);
                }
                else
                {
                    cardView.setVisibility(View.GONE);
                }


            }
        });

        return view;
    }

}
