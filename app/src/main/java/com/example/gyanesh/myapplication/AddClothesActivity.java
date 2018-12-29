package com.example.gyanesh.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.gyanesh.myapplication.Models.Garment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddClothesActivity extends AppCompatActivity implements AddClothesAdapter.Listener {

    List<Garment> garments;
    Map<Integer, Garment> selectedGarments;
    TextView total_amt;
    int total_amount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_clothes);
        garments = new ArrayList<>();
        garments.add(new Garment(1,"Shirt", 6));
        garments.add(new Garment(2,"Jacket", 15));
        garments.add(new Garment(3,"Bed Sheet", 15));
        garments.add(new Garment(4,"Towel", 10));
        garments.add(new Garment(5,"Shirt", 6));
        garments.add(new Garment(6,"Jacket", 15));
        garments.add(new Garment(7,"Bed Sheet", 15));
        garments.add(new Garment(8,"Towel", 10));
        garments.add(new Garment(9,"Shirt", 6));
        garments.add(new Garment(10,"Jacket", 15));
        garments.add(new Garment(11,"Bed Sheet", 15));
        garments.add(new Garment(12,"Towel", 10));
        garments.add(new Garment(13,"Shirt", 6));
        garments.add(new Garment(14,"Jacket", 15));
        garments.add(new Garment(15,"Bed Sheet", 15));
        garments.add(new Garment(16,"Towel", 10));
        garments.add(new Garment(17,"Shirt", 6));
        garments.add(new Garment(18,"Jacket", 15));
        garments.add(new Garment(19,"Bed Sheet", 15));
        garments.add(new Garment(20,"Towel", 10));
        garments.add(new Garment(21,"Shirt", 6));
        garments.add(new Garment(22,"Jacket", 15));
        garments.add(new Garment(23,"Bed Sheet", 15));
        garments.add(new Garment(24,"Towel", 10));
        total_amt = findViewById(R.id.total_amount);
        selectedGarments = new HashMap<>();
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(new AddClothesAdapter(this, garments));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        TextView done = findViewById(R.id.done);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("selectedGarments",(Serializable)selectedGarments);
                setResult(Activity.RESULT_OK,intent);
                finish();
            }
        });

    }

    @Override
    public void updateSelected(Garment item,int prevCount) {

        if (selectedGarments.containsKey(item.getGarmentId())) {
            total_amount -= item.getPrice()*prevCount;
            total_amount += item.getPrice()*item.getNumber();

        } else {
            total_amount += item.getPrice() * item.getNumber();
            selectedGarments.put(item.getGarmentId(), item);
        }
        total_amt.setText(String.valueOf(total_amount));
    }
}
