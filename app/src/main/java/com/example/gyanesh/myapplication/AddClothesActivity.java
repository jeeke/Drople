package com.example.gyanesh.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.gyanesh.myapplication.Adapters.AddClothesAdapter;
import com.example.gyanesh.myapplication.Models.Garment;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.gyanesh.myapplication.utilClasses.CloudDbHelper.getGarmentsList;

public class AddClothesActivity extends AppCompatActivity implements AddClothesAdapter.Listener {

    List<Garment> garments;
    Map<String, Garment> selectedGarments;
    TextView total_amt;
    int total_amount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_clothes);
        garments = getGarmentsList("DR. B R AMBEDKAR NIT JALANDHAR",0);
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

        if (selectedGarments.containsKey(item.getObjectId())) {
            total_amount -= item.getPrice()*prevCount;
            total_amount += item.getPrice()*item.getCount();

        } else {
            total_amount += item.getPrice() * item.getCount();
            selectedGarments.put(item.getObjectId(), item);
        }
        total_amt.setText(String.valueOf(total_amount));
    }
}
