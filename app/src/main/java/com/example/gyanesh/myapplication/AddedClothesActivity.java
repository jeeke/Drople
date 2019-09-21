package com.example.gyanesh.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.gyanesh.myapplication.Adapters.AddClothAdaptor;
import com.example.gyanesh.myapplication.Adapters.VendorAdaptor;
import com.google.android.material.tabs.TabLayout;

public class AddedClothesActivity extends AppCompatActivity {
    public static Integer amount = 0;
    public static TextView amontdisp;
    ViewPager viewPager;
    TabLayout tabLayout;
    ViewGroup layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_added_clothes);
        viewPager = findViewById(R.id.inductionpager);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager.setAdapter(new AddClothAdaptor(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);
        amontdisp = findViewById(R.id.amount);
    }
}
