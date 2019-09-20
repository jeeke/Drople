package com.example.gyanesh.myapplication.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gyanesh.myapplication.Adapters.VendorAdaptor;
import com.example.gyanesh.myapplication.Adapters.VendorListAdaptor;
import com.example.gyanesh.myapplication.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class VendorFragmentPending extends Fragment {
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