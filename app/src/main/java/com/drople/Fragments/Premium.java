package com.drople.Fragments;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.drople.Adapters.AddClothListAdaptor;
import com.drople.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Premium extends Fragment {
    RecyclerView recyclerView;
    FloatingActionButton btn;

    ArrayList<String> clothes = new ArrayList<String>(){
        {
            add("Shirt");
            add("Jeans");
            add("T-Shirt");
            add("Blanket");
            add("Jacket");
            add("Curtain");
            add("Shorts");
            add("Bed Sheets");
            add("Inners");
            add("Bag");
        }
    };

    ArrayList<Integer> cost = new ArrayList<Integer>()
    {
        {
            add(7);
            add(10);
            add(5);
            add(25);
            add(15);
            add(20);
            add(8);
            add(20);
            add(8);
            add(25);
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View rootView = inflater.inflate(R.layout.fragment_addcloth, container, false);
        recyclerView = rootView.findViewById(R.id.addclothrv);
        AddClothListAdaptor mAdapter = new AddClothListAdaptor(clothes,cost);//response.body().getData());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(rootView.getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        return rootView;
    }
}