package com.example.gyanesh.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.ViewGroup;

import com.example.gyanesh.myapplication.Adapters.VendorAdaptor;
import com.google.android.material.tabs.TabLayout;

public class VendorActivity extends AppCompatActivity {

    ViewPager viewPager;
    TabLayout tabLayout;
    ViewGroup layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor);
        viewPager = findViewById(R.id.inductionpager);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager.setAdapter(new VendorAdaptor(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);
    }
}
