package com.example.gyanesh.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.gyanesh.myapplication.Models.Garment;

import java.util.ArrayList;
import java.util.List;

public class AddClothesActivity extends AppCompatActivity {

    List<Garment> garments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_clothes);
        garments.add(new Garment("Shirt",6));
        garments.add(new Garment("Jacket",15));
        garments.add(new Garment("Bed Sheet",15));
        garments.add(new Garment("Towel",10));
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(new AddClothesAdapter(garments));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
