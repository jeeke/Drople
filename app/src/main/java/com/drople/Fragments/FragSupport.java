package com.drople.Fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.drople.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragSupport extends Fragment {
    CardView callus,mailus;

    public FragSupport() {
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
        callus = view.findViewById(R.id.callus);
        callus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:+918652751124"));
                startActivity(intent);
            }
        });
        mailus = view.findViewById(R.id.mailus);
        mailus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent (Intent.ACTION_SEND);
                intent.setType("message/rfc822");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"dropple@gmail.com"});
                intent.putExtra(Intent.EXTRA_SUBJECT, "Feed Back");
                intent.setPackage("com.google.android.gm");
                    startActivity(intent);
            }

        });
        constraintLayoutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ConstraintLayout cardView = view.findViewById(R.id.aboutus);
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

                ConstraintLayout cardView = view.findViewById(R.id.contactus);
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

                ConstraintLayout cardView = view.findViewById(R.id.ourpolicies);
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

                ConstraintLayout cardView = view.findViewById(R.id.faqs);
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

    public void call(View v)
    {

    }
    public void mail(View v)
    {

    }

}
