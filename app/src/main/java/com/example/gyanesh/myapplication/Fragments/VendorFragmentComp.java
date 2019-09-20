package com.example.gyanesh.myapplication.Fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.drople.R;
import com.example.gyanesh.myapplication.Adapters.VendorListAdaptor;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class VendorFragmentComp extends Fragment {
    RecyclerView recyclerView;
    FloatingActionButton btn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View rootView = inflater.inflate(R.layout.fragment_vendor, container, false);
        recyclerView = rootView.findViewById(R.id.vendorrv);
        myapivendor("1",rootView);
        return rootView;
    }

    public void myapivendor(String no,View rootview)
    {
        VendorListAdaptor mAdapter = new VendorListAdaptor(10);//response.body().getData());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(rootview.getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
    }
}