package com.example.gyanesh.myapplication.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gyanesh.myapplication.R;

import androidx.fragment.app.Fragment;

public class wash_iron extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       final View view= inflater.inflate(R.layout.fragment_wash_iron, container, false);
       return view;
    }
}
